package com.app.expertsubmission.core.di

import com.app.expertsubmission.core.data.ArticleRepository
import com.app.expertsubmission.core.domain.repository.IArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(articleRepository: ArticleRepository): IArticleRepository

}