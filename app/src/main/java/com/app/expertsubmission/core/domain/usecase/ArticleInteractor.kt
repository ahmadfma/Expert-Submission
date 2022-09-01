package com.app.expertsubmission.core.domain.usecase

import com.app.expertsubmission.core.data.Resource
import com.app.expertsubmission.core.domain.model.Article
import com.app.expertsubmission.core.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleInteractor @Inject constructor(private val articleRepository: IArticleRepository) : ArticleUseCase {
    override fun getTopArticles(): Flow<Resource<List<Article>>> = articleRepository.getTopArticles()
}