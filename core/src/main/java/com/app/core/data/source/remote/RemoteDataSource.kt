package com.app.core.data.source.remote

import android.util.Log
import com.app.core.data.source.remote.network.ApiResponse
import com.app.core.data.source.remote.network.ApiService
import com.app.core.data.source.remote.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

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

}