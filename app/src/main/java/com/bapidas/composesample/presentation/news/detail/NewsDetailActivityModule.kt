package com.bapidas.composesample.presentation.news.detail

import androidx.appcompat.app.AppCompatActivity
import com.bapidas.composesample.presentation.base.activity.BaseActivityModule
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.key.ActivityViewModelKey
import com.bapidas.composesample.presentation.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class])
abstract class NewsDetailActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: NewsDetailsActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(NewsDetailViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(viewModel: NewsDetailViewModel): BaseActivityViewModel
}