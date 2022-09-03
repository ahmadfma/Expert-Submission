package com.app.expertsubmission.ui.detail

import androidx.lifecycle.ViewModel
import com.app.core.data.source.local.entity.ArticleEntity
import com.app.core.domain.model.Article
import com.app.core.domain.usecase.ArticleUseCase

class DetailViewModel (private val articleUseCase: ArticleUseCase) : ViewModel() {
    var selectedArticle: Article? = null

    fun setFavoriteArticle(article: Article, state: Boolean) = articleUseCase.setFavoriteArticle(article, state)

    suspend fun insertArticle(article: ArticleEntity) = articleUseCase.insertArticle(article)

    suspend fun getArticleByImageUrl(imageUrl: String) = articleUseCase.getArticleByImageUrl(imageUrl)

}