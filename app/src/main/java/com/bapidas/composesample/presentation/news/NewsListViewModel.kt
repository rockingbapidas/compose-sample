package com.bapidas.composesample.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bapidas.composesample.BuildConfig
import com.bapidas.composesample.data.repository.NewsRepository
import com.bapidas.composesample.data.repository.NewsRepositoryImpl
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.scope.ActivityScope
import com.bapidas.composesample.presentation.model.Article
import com.bapidas.composesample.presentation.news.paging.NewsBoundaryCallback
import com.bapidas.composesample.presentation.news.paging.NewsDataSourceFactory
import javax.inject.Inject

@ActivityScope
class NewsListViewModel @Inject constructor(private val mNewsRepository: NewsRepository) :
    BaseActivityViewModel() {

    //Live data Paged List
    val newsArticles by lazy { buildLiveDataList() }

    private fun buildLiveDataList(): LiveData<PagedList<Article>> {
        val mNewsDataSourceFactory by lazy {
            if (BuildConfig.LOCAL_CACHE)
                mNewsRepository.getNewsArticles()
            else
                NewsDataSourceFactory(mNewsRepository, viewModelScope)
        }
        val mPagedListConfig by lazy {
            PagedList.Config.Builder()
                .setPageSize(NewsRepositoryImpl.PAGE_SIZE)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(5)
                .build()
        }
        return if (BuildConfig.LOCAL_CACHE) {
            LivePagedListBuilder(mNewsDataSourceFactory, mPagedListConfig)
                .setBoundaryCallback(NewsBoundaryCallback(mNewsRepository, viewModelScope))
                .build()
        } else {
            LivePagedListBuilder(mNewsDataSourceFactory, mPagedListConfig)
                .build()
        }
    }
}