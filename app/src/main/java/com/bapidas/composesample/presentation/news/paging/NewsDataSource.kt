package com.bapidas.composesample.presentation.news.paging

import androidx.paging.ItemKeyedDataSource
import com.bapidas.composesample.data.mapper.toArticle
import com.bapidas.composesample.data.repository.NewsRepository
import com.bapidas.composesample.data.repository.NewsRepositoryImpl
import com.bapidas.composesample.data.repository.NewsRepositoryImpl.Companion.INITIAL_PAGE
import com.bapidas.composesample.presentation.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsDataSource(
    private val mNewsRepository: NewsRepository,
    private val mCoroutineScope: CoroutineScope
) : ItemKeyedDataSource<String, Article>() {

    private var totalNewsArticle = 0
    private var loadedNewsArticle = 0

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<Article>
    ) {
        Timber.v("loadInitial ")
        mCoroutineScope.launch { callback.onResult(loadNews(INITIAL_PAGE)) }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Article>) {
        Timber.v("loadAfter ")
        val isItemPending = loadedNewsArticle < totalNewsArticle
        val nextPage = loadedNewsArticle.div(NewsRepositoryImpl.PAGE_SIZE).plus(1)
        if (isItemPending) mCoroutineScope.launch { callback.onResult(loadNews(nextPage)) }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Article>) {
        Timber.v("loadBefore ")
    }

    override fun getKey(item: Article): String {
        Timber.v("getKey %s ", item.toString())
        return item.publishedAt
    }

    private suspend fun loadNews(page: Int): List<Article> {
        Timber.v("loadNews ")
        try {
            val result = mNewsRepository.fetchNewsFromRemote(page)
            loadedNewsArticle += NewsRepositoryImpl.PAGE_SIZE
            totalNewsArticle = result.totalResults
            return result.articles.map { it.toArticle() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}