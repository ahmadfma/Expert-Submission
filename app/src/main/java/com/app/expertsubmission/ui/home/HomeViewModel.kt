package com.app.expertsubmission.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.core.domain.usecase.ArticleUseCase

class HomeViewModel (articleUseCase: ArticleUseCase) : ViewModel() {
    val topArticles = articleUseCase.getTopArticles().asLiveData()
}