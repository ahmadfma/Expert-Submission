package com.app.core.data.source.remote

import com.app.core.DataDummy
import com.app.core.FakeApiService
import com.app.core.data.source.remote.response.ArticlesItem
import com.app.core.data.source.remote.response.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private lateinit var apiService: FakeApiService

    @Before
    fun setUp() {
        apiService = FakeApiService()
    }

    @Test
    fun `getTopArticles should return list of articleItem`() = runTest {
        val expectedValue = DataDummy.generateDummyArticles()
        val actualValue = apiService.getTopArticles("", "")
        Assert.assertNotNull(actualValue)
        Assert.assertEquals(actualValue.articles, expectedValue)
    }

    @Test
    fun `searchArticles should return filtered list of articleItem`() = runTest {
        val expectedValue = ArticlesItem(
            "2022-09-06T00:50:54Z",
            "ahmad3",
            "url3",
            "desc3",
            Source("sourcename3", "3"),
            "title3",
            "url3",
            "content3",
        )
        val actualValue = apiService.searchArticles("title3")
        Assert.assertNotNull(actualValue)
        Assert.assertEquals(actualValue.articles.first { it.title == "title3" }, expectedValue)
    }


}