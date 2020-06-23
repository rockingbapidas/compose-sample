package com.bapidas.composesample.presentation.news.detail

import androidx.ui.core.setContent
import com.bapidas.composesample.presentation.base.activity.BaseActivity
import com.bapidas.composesample.presentation.base.extension.makeStatusBarTransparent

class NewsDetailsActivity : BaseActivity<NewsDetailViewModel>() {

    override val viewModelClass: Class<NewsDetailViewModel> = NewsDetailViewModel::class.java

    override fun onViewModelCreated() {
        super.onViewModelCreated()
        makeStatusBarTransparent()
        setContent {
            NewsDetailScreen(viewModel)
        }
    }

    companion object {
        const val NEWS_EXTRA_DATA = "newExtra"
    }
}