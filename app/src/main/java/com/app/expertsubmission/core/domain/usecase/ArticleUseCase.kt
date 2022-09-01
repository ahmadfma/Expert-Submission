package com.app.expertsubmission.core.domain.usecase

import com.app.expertsubmission.core.data.Resource
import com.app.expertsubmission.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {
    fun getTopArticles(): Flow<Resource<List<Article>>>
}