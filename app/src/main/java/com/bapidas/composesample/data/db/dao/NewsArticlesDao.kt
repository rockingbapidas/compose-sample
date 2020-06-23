package com.bapidas.composesample.data.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.bapidas.composesample.data.db.model.ArticleEntity

@Dao
interface NewsArticlesDao : BaseDao<ArticleEntity> {
    @Query("SELECT * FROM tbl_article")
    fun getNewsArticles(): DataSource.Factory<Int, ArticleEntity>

    @Query("SELECT * FROM tbl_article WHERE publishedAt = :id")
    suspend fun getNewsArticle(id: String): ArticleEntity

    @Query("DELETE FROM tbl_article WHERE publishedAt = :id")
    suspend fun deleteNewsArticle(id: String)

    @Query("SELECT COUNT(publishedAt) FROM tbl_article")
    suspend fun getNewsArticlesCount(): Int
}