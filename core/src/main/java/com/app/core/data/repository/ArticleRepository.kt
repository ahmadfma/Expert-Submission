package com.app.core.data.repository

import com.app.core.data.Resource
import com.app.core.data.source.local.LocalDataSource
import com.app.core.data.source.remote.RemoteDataSource
import com.app.core.data.source.remote.network.ApiResponse
import com.app.core.data.source.remote.response.ArticlesItem
import com.app.core.domain.model.Article
import com.app.core.domain.repository.IArticleRepository
import com.app.core.utils.AppExecutors
import com.app.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IArticleRepository {

    override fun getTopArticles(): Flow<Resource<List<Article>>> =
        object : com.app.core.data.NetworkBoundResource<List<Article>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<Article>> = localDataSource.getAllArticle().map {
                DataMapper.mapEntitiesToDomain(it)
            }
            override fun shouldFetch(data: List<Article>?): Boolean = true
            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> = remoteDataSource.getTopArticles()
            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val articles = DataMapper.mapResponseToEntities(data)
                localDataSource.insertArticles(articles)
            }
        }.asFlow()


}