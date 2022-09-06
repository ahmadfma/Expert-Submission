package com.app.core

import com.app.core.data.source.remote.response.ArticlesItem
import com.app.core.data.source.remote.response.Source

object DataDummy {

    fun generateDummyArticles(): List<ArticlesItem> {
        val list = mutableListOf<ArticlesItem>()
        for(i in 0..10) {
            list.add(
                ArticlesItem(
                    "2022-09-06T00:50:54Z",
                    "ahmad$i",
                    "url$i",
                    "desc$i",
                    Source("sourcename$i", "$i"),
                    "title$i",
                    "url$i",
                    "content$i",
                )
            )
        }
        return list
    }

}