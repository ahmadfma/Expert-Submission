package com.app.expertsubmission.di

import com.app.core.domain.usecase.ArticleInteractor
import com.app.core.domain.usecase.ArticleUseCase
import com.app.expertsubmission.ui.detail.DetailViewModel
import com.app.expertsubmission.ui.home.HomeViewModel
import com.app.expertsubmission.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ArticleUseCase> { ArticleInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}