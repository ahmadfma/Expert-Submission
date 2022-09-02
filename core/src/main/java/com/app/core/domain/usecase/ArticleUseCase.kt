package com.app.core.domain.usecase

import com.app.core.data.Resource
import com.app.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {
    fun getTopArticles(): Flow<Resource<List<Article>>>
}