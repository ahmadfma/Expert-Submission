package com.app.expertsubmission.core.di

import android.content.Context
import androidx.room.Room
import com.app.expertsubmission.core.data.source.local.room.ArticleDao
import com.app.expertsubmission.core.data.source.local.room.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java, "Article.db"
    ).fallbackToDestructiveMigration().build()

    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao = articleDatabase.articleDao()

}