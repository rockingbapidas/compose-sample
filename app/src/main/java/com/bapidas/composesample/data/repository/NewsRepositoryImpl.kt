package com.bapidas.composesample.data.repository

import androidx.paging.DataSource
import com.bapidas.composesample.BuildConfig
import com.bapidas.composesample.data.db.dao.NewsArticlesDao
import com.bapidas.composesample.data.mapper.toArticle
import com.bapidas.composesample.data.mapper.toArticleEntity
import com.bapidas.composesample.data.network.api.NewsApi
import com.bapidas.composesample.data.network.model.News
import com.bapidas.composesample.data.network.response.NewsListResponse
import com.bapidas.composesample.presentation.model.Article

class NewsRepositoryImpl constructor(
    private val mNewsApi: NewsApi,
    private val mNewsArticlesDao: NewsArticlesDao
) : NewsRepository {

    override fun getNewsArticles(): DataSource.Factory<Int, Article> {
        return mNewsArticlesDao.getNewsArticles().map { it.toArticle() }
    }

    override suspend fun getNewsArticle(id: String): Article {
        return mNewsArticlesDao.getNewsArticle(id).toArticle()
    }

    override suspend fun getNewsArticleTotalCount(): Int {
        return mNewsArticlesDao.getNewsArticlesCount()
    }

    override suspend fun deleteNewsArticle(article: Article) {
        mNewsArticlesDao.delete(article.toArticleEntity())
    }

    override suspend fun deleteNewsArticles(articles: List<Article>) {
        mNewsArticlesDao.delete(articles.map { it.toArticleEntity() })
    }

    override suspend fun saveNewsArticles(articles: List<News>) {
        mNewsArticlesDao.insert(articles.map { it.toArticleEntity() })
    }

    override suspend fun saveNewsArticle(article: News) {
        mNewsArticlesDao.insert(article.toArticleEntity())
    }

    override suspend fun fetchNewsFromRemote(page: Int): NewsListResponse {
        return mNewsApi.getNewsArticles(CATEGORY, PAGE_SIZE, page, BuildConfig.API_KEY)
    }

    companion object {
        const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 20
        const val CATEGORY = "business"
    }
}