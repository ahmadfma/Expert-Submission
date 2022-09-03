package com.app.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.core.domain.usecase.ArticleUseCase

class ListFavoriteViewModel(private val articleUseCase: ArticleUseCase) : ViewModel() {
    fun getFavoriteArticles() = articleUseCase.getFavoriteArticles().asLiveData()
}