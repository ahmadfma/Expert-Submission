package com.app.expertsubmission.core.domain.model

import com.app.expertsubmission.core.data.source.remote.response.Source

data class Articles(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val content: Any? = null
)