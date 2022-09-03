package com.app.expertsubmission.ui.detail

import androidx.lifecycle.ViewModel
import com.app.core.domain.model.Article
import com.app.core.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val articleUseCase: ArticleUseCase) : ViewModel() {
    var selectedArticle: Article? = null

    fun setFavoriteArticle(article: Article, state: Boolean) = articleUseCase.setFavoriteArticle(article, state)
}