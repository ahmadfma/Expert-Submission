package com.app.core.domain.repository

import com.app.core.data.Resource
import com.app.core.data.source.local.entity.ArticleEntity
import com.app.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    fun getTopArticles(): Flow<Resource<List<Article>>>

    suspend fun searchArticles(keyword: String): Flow<Resource<List<Article>>>

    fun searchFavoriteArticles(keyword: String): Flow<List<ArticleEntity>>

    fun getFavoriteArticles(): Flow<List<Article>>

    suspend fun insertArticle(article: ArticleEntity)

    suspend fun getArticleByImageUrl(imageUrl: String): List<ArticleEntity>

    fun setFavoriteArticle(article: Article, state: Boolean)

}