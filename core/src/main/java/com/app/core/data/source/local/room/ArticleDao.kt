package com.app.core.data.source.local.room

import androidx.room.*
import com.app.core.data.source.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticle(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE isFavorite = 1")
    fun getFavoriteArticle(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: ArticleEntity)

    @Update
    fun updateArticle(article: ArticleEntity)

    @Query("SELECT * FROM article WHERE title = :title")
    suspend fun getArticleByTitle(title: String): List<ArticleEntity>
}