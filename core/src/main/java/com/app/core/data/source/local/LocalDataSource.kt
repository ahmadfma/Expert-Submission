package com.app.core.data.source.local

import com.app.core.data.source.local.entity.ArticleEntity
import com.app.core.data.source.local.room.ArticleDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val articleDao: ArticleDao) {

    fun getAllArticle(): Flow<List<ArticleEntity>> = articleDao.getAllArticle()

    fun getFavoriteArticle(): Flow<List<ArticleEntity>> = articleDao.getFavoriteArticle()

    suspend fun insertArticles(articles: List<ArticleEntity>) = articleDao.insertArticles(articles)

    fun setFavoriteArticle(article: ArticleEntity, newState: Boolean) {
        article.isFavorite = newState
        articleDao.updateArticle(article)
    }

}