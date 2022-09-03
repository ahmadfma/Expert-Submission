package com.app.core.domain.usecase

import com.app.core.data.Resource
import com.app.core.data.source.local.entity.ArticleEntity
import com.app.core.domain.model.Article
import com.app.core.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleInteractor (private val articleRepository: IArticleRepository) : ArticleUseCase {
    override fun getTopArticles(): Flow<Resource<List<Article>>> = articleRepository.getTopArticles()
    override suspend fun searchArticle(keyword: String): Flow<Resource<List<Article>>> = articleRepository.searchArticles(keyword)
    override fun getFavoriteArticles(): Flow<List<Article>> = articleRepository.getFavoriteArticles()
    override suspend fun insertArticle(article: ArticleEntity) = articleRepository.insertArticle(article)
    override fun getArticleByImageUrl(article: ArticleEntity): List<ArticleEntity> = articleRepository.getArticleByImageUrl(article)
    override fun setFavoriteArticle(article: Article, state: Boolean) = articleRepository.setFavoriteArticle(article, state)
}