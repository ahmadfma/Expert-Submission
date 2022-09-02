package com.app.core.utils

import com.app.core.data.source.local.entity.ArticleEntity
import com.app.core.data.source.remote.response.ArticlesItem
import com.app.core.domain.model.Article

object DataMapper {

    fun mapResponseToEntities(input: List<ArticlesItem>): List<ArticleEntity> = input.map {
        ArticleEntity(
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            author = it.author,
            description = it.description,
            sourceName = it.source?.name,
            title = it.title,
            url = it.url,
            content = it.content,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<ArticleEntity>): List<Article> = input.map {
        Article(
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            author = it.author,
            description = it.description,
            sourceName = it.sourceName,
            title = it.title,
            url = it.url,
            content = it.content,
            isFavorite = it.isFavorite
        )
    }

    fun mapDomainToEntity(input: Article) = ArticleEntity(
        urlToImage = input.urlToImage,
        publishedAt = input.publishedAt,
        author = input.author,
        description = input.description,
        sourceName = input.sourceName,
        title = input.title,
        url = input.url,
        content = input.content,
        isFavorite = input.isFavorite
    )
}