package com.bapidas.composesample.presentation.news.detail

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.scope.ActivityScope
import com.bapidas.composesample.presentation.model.Article
import javax.inject.Inject

@ActivityScope
class NewsDetailViewModel @Inject constructor() :
    BaseActivityViewModel() {
    val article = MutableLiveData<Article>()

    override fun handleIntent(intent: Intent) {
        super.handleIntent(intent)
        intent.extras?.apply {
            article.value = getSerializable(NewsDetailsActivity.NEWS_EXTRA_DATA) as Article
        }
    }
}