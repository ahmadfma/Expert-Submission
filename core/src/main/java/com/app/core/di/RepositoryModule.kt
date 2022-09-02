package com.app.core.di

import com.app.core.domain.repository.IArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(articleRepository: com.app.core.data.repository.ArticleRepository): IArticleRepository

}