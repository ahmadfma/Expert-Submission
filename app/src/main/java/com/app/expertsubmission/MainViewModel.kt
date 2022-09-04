package com.app.expertsubmission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.core.domain.usecase.ArticleUseCase

class MainViewModel(private val articleUseCase: ArticleUseCase): ViewModel() {
    suspend fun searchArticle(keyword: String) = articleUseCase.searchArticle(keyword).asLiveData()
}