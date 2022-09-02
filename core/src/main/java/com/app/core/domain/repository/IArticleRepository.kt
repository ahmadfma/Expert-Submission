package com.app.core.domain.repository

import com.app.core.data.Resource
import com.app.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    fun getTopArticles(): Flow<Resource<List<Article>>>

}