package com.app.core.data.source.remote

import android.util.Log
import com.app.core.data.Resource
import com.app.core.data.source.remote.network.ApiResponse
import com.app.core.data.source.remote.network.ApiService
import com.app.core.data.source.remote.response.ArticlesItem
import com.app.core.domain.model.Article
import com.app.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getTopArticles(): Flow<ApiResponse<List<ArticlesItem>>> = flow {
        try {
            val response = apiService.getTopArticles()
            val articles = response.articles
            Log.d("RemoteDataSource", "getTopArticles: $articles")
            if(articles.isEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(articles))
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", "getTopArticles: $e")
        }
    }.flowOn(Dispatchers.IO)

    suspend fun searchArticles(keyword: String): Flow<Resource<List<Article>>> = flow {
        try {
            val response = apiService.searchArticles(keyword)
            val articles = DataMapper.mapResponseToDomain(response.articles)
            Log.d("RemoteDataSource", "getTopArticles: $articles")
            emit(Resource.Success(articles))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
            Log.e("RemoteDataSource", "getTopArticles: $e")
        }
    }

}