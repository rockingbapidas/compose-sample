package com.bapidas.composesample.presentation.news.paging

import androidx.paging.PagedList
import com.bapidas.composesample.data.repository.NewsRepository
import com.bapidas.composesample.data.repository.NewsRepositoryImpl
import com.bapidas.composesample.data.repository.NewsRepositoryImpl.Companion.INITIAL_PAGE
import com.bapidas.composesample.presentation.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsBoundaryCallback(
    private val mNewsRepository: NewsRepository,
    private val mCoroutineScope: CoroutineScope
) : PagedList.BoundaryCallback<Article>() {
    private var latestLoad = true
    private var totalNewsArticle = 0
    private var loadedNewsArticle = 0

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Timber.v("onZeroItemsLoaded")
        latestLoad = false
        mCoroutineScope.launch { loadNewsArticles(INITIAL_PAGE, latestLoad) }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Article) {
        super.onItemAtFrontLoaded(itemAtFront)
        Timber.v("onItemAtFrontLoaded")
        if (latestLoad) {
            mCoroutineScope.launch { loadNewsArticles(INITIAL_PAGE, latestLoad) }
            latestLoad = false
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        super.onItemAtEndLoaded(itemAtEnd)
        Timber.v("onItemAtEndLoaded")
        mCoroutineScope.launch { loadMoreNewsArticles() }
    }

    private suspend fun loadMoreNewsArticles() {
        Timber.v("loadMoreNewsArticles ")
        val isItemPending = loadedNewsArticle < totalNewsArticle
        val nextPage = loadedNewsArticle.div(NewsRepositoryImpl.PAGE_SIZE).plus(1)
        if (isItemPending) loadNewsArticles(nextPage, latestLoad)
    }

    private suspend fun loadNewsArticles(nextPage: Int, latestLoad: Boolean) {
        val result = mNewsRepository.fetchNewsFromRemote(nextPage)
        if (latestLoad)
            loadedNewsArticle = mNewsRepository.getNewsArticleTotalCount()
        else
            loadedNewsArticle += NewsRepositoryImpl.PAGE_SIZE
        totalNewsArticle = result.totalResults
        mNewsRepository.saveNewsArticles(result.articles)
    }
}