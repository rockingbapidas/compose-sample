package com.bapidas.composesample.presentation.di

import com.bapidas.composesample.presentation.di.scope.ActivityScope
import com.bapidas.composesample.presentation.news.NewsActivity
import com.bapidas.composesample.presentation.news.NewsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [NewsActivityModule::class])
    @ActivityScope
    abstract fun contributeNewsActivity(): NewsActivity
}
