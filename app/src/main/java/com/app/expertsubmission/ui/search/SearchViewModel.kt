package com.app.expertsubmission.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.core.domain.usecase.ArticleUseCase

class SearchViewModel(private val articleUseCase: ArticleUseCase): ViewModel() {
    suspend fun searchArticle(keyword: String) = articleUseCase.searchArticle(keyword).asLiveData()
}