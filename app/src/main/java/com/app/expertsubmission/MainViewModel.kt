package com.app.expertsubmission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.core.domain.usecase.ArticleUseCase
import kotlinx.coroutines.withContext

class MainViewModel(private val articleUseCase: ArticleUseCase): ViewModel() {
    suspend fun searchArticle(keyword: String) = articleUseCase.searchArticle(keyword).asLiveData()
    fun searchFavoriteArticle(keyword: String) = articleUseCase.searchFavoriteArticle(keyword).asLiveData()
}