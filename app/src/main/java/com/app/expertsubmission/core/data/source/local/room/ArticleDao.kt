package com.app.expertsubmission.core.data.source.local.room

import androidx.room.*
import com.app.expertsubmission.core.data.source.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticle(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE isFavorite = 1")
    fun getFavoriteArticle(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Update
    fun updateArticle(article: ArticleEntity)
}