package com.app.expertsubmission.di

import com.app.expertsubmission.core.domain.usecase.ArticleInteractor
import com.app.expertsubmission.core.domain.usecase.ArticleUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideArticleUseCase(articleInteractor: ArticleInteractor): ArticleUseCase

}