package com.bapidas.composesample.presentation.news.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bapidas.composesample.data.repository.NewsRepository
import com.bapidas.composesample.presentation.model.Article
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class NewsDataSourceFactory(
    private val mNewsRepository: NewsRepository,
    private val mCoroutineScope: CoroutineScope
) : DataSource.Factory<String, Article>() {

    private val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<String, Article> {
        Timber.v("create")
        val mNewsDataSource = NewsDataSource(mNewsRepository, mCoroutineScope)
        newsDataSourceLiveData.postValue(mNewsDataSource)
        return mNewsDataSource
    }
}