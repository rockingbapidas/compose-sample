package com.bapidas.composesample.presentation.news

import androidx.ui.core.setContent
import com.bapidas.composesample.presentation.base.activity.BaseActivity

class NewsListActivity : BaseActivity<NewsListViewModel>() {

    override val viewModelClass: Class<NewsListViewModel>
        get() = NewsListViewModel::class.java

    override fun onViewModelCreated() {
        super.onViewModelCreated()
        setContent { NewsScreen(viewModel) }
    }
}