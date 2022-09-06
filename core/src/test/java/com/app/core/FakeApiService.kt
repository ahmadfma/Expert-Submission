package com.app.core

import com.app.core.data.source.remote.network.ApiService
import com.app.core.data.source.remote.response.ArticlesResponse

class FakeApiService: ApiService {
    private val dataDummy = DataDummy.generateDummyArticles()

    override suspend fun getTopArticles(country: String, apiKey: String): ArticlesResponse {
        return ArticlesResponse(
            dataDummy.size,
            dataDummy,
            "Success"
        )
    }

    override suspend fun searchArticles(
        keyword: String,
        sortBy: String,
        language: String,
        apiKey: String,
    ): ArticlesResponse {
        return ArticlesResponse(
            dataDummy.size,
            dataDummy.filter { it.title.contains(keyword, false) },
            "Success"
        )
    }
}