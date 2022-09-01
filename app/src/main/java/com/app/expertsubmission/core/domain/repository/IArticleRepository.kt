package com.app.expertsubmission.core.domain.repository

import com.app.expertsubmission.core.data.Resource
import com.app.expertsubmission.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    fun getTopArticles(): Flow<Resource<List<Article>>>

}