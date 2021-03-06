package com.bapidas.composesample.presentation.news

import androidx.ui.core.setContent
import com.bapidas.composesample.presentation.base.activity.BaseActivity
import com.bapidas.composesample.presentation.news.screens.AppContent
import com.bapidas.composesample.presentation.news.screens.NewsStatus
import com.bapidas.composesample.presentation.news.screens.Screen
import com.bapidas.composesample.presentation.news.screens.navigateTo

class NewsListActivity : BaseActivity<NewsListViewModel>() {

    override val viewModelClass: Class<NewsListViewModel>
        get() = NewsListViewModel::class.java

    override fun onViewModelCreated() {
        super.onViewModelCreated()
        setContent {
            AppContent(
                viewModel
            )
        }
    }

    override fun onBackPressed() {
        if (NewsStatus.currentScreen != Screen.NewsList) {
            navigateTo(
                Screen.NewsList
            )
            return
        }
        super.onBackPressed()
    }
}