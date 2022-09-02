package com.app.core.domain.model

data class Article(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String,
    val description: String? = null,
    val sourceName: String? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null,
    var isFavorite: Boolean
)