package com.bapidas.composesample.presentation.di

import com.bapidas.composesample.presentation.di.scope.ActivityScope
import com.bapidas.composesample.presentation.news.NewsListActivity
import com.bapidas.composesample.presentation.news.NewsListActivityModule
import com.bapidas.composesample.presentation.news.detail.NewsDetailActivityModule
import com.bapidas.composesample.presentation.news.detail.NewsDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [NewsListActivityModule::class])
    @ActivityScope
    abstract fun contributeNewsActivity(): NewsListActivity

    @ContributesAndroidInjector(modules = [NewsDetailActivityModule::class])
    @ActivityScope
    abstract fun contributeNewsDetailsActivity(): NewsDetailsActivity
}
