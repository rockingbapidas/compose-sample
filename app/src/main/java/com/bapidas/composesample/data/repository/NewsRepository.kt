package com.bapidas.composesample.data.repository

import androidx.paging.DataSource
import com.bapidas.composesample.data.network.model.News
import com.bapidas.composesample.data.network.response.NewsListResponse
import com.bapidas.composesample.presentation.model.Article

interface NewsRepository {
    fun getNewsArticles(): DataSource.Factory<Int, Article>

    suspend fun getNewsArticle(id: String): Article

    suspend fun getNewsArticleTotalCount(): Int

    suspend fun deleteNewsArticle(article: Article)

    suspend fun deleteNewsArticles(articles: List<Article>)

    suspend fun saveNewsArticles(articles: List<News>)

    suspend fun saveNewsArticle(article: News)

    suspend fun fetchNewsFromRemote(page: Int): NewsListResponse
}