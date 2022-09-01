package com.app.expertsubmission.core.data.source.remote.network

import com.app.expertsubmission.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopArticles(
        @Query("country") country: String = "id",
        @Query("apikey") apiKey: String = "b20c0ec3d33f4ed599f61e82a2a7484e"
    ): NewsResponse

}