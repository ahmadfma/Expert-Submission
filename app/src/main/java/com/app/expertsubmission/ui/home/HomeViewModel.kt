package com.app.expertsubmission.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.core.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(articleUseCase: ArticleUseCase) : ViewModel() {
    val topArticles = articleUseCase.getTopArticles().asLiveData()
}