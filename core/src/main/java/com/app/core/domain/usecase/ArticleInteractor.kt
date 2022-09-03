package com.app.core.domain.usecase

import com.app.core.data.Resource
import com.app.core.domain.model.Article
import com.app.core.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleInteractor @Inject constructor(private val articleRepository: IArticleRepository) : ArticleUseCase {
    override fun getTopArticles(): Flow<Resource<List<Article>>> = articleRepository.getTopArticles()
    override fun getFavoriteArticles(): Flow<List<Article>> = articleRepository.getFavoriteArticles()
    override fun setFavoriteArticle(article: Article, state: Boolean) = articleRepository.setFavoriteArticle(article, state)
}