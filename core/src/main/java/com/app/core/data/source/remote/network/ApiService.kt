package com.app.core.data.source.remote.network

import com.app.core.data.*
import com.app.core.data.source.remote.response.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(TOP_HEADLINES)
    suspend fun getTopArticles(
        @Query(COUNTRY) country: String = COUNTRY_VALUE,
        @Query(API_KEY) apiKey: String = API_KEY_VALUE
    ): ArticlesResponse

    @GET(EVERYTHING)
    suspend fun searchArticles(
        @Query("q") keyword: String,
        @Query(SORT_BY) sortBy: String = SORT_BY_VALUE,
        @Query(LANGUAGE) language: String = COUNTRY_VALUE,
        @Query(API_KEY) apiKey: String = API_KEY_VALUE
    ): ArticlesResponse

}