package com.app.expertsubmission.core.data

import com.app.expertsubmission.core.data.source.remote.RemoteDataSource
import com.app.expertsubmission.core.data.source.remote.network.ApiResponse
import com.app.expertsubmission.core.data.source.remote.response.ArticlesItem
import com.app.expertsubmission.core.domain.model.Articles
import com.app.expertsubmission.core.domain.repository.IArticleRepository
import com.app.expertsubmission.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): IArticleRepository{

    override fun getTopArticles(): Flow<Resource<List<Articles>>> =
        object : NetworkBoundResource<List<Articles>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<Articles>> = flow {  }
            override fun shouldFetch(data: List<Articles>?): Boolean = true
            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> = remoteDataSource.getTopArticles()
            override suspend fun saveCallResult(data: List<ArticlesItem>) {}
        }.asFlow()

}