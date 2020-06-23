package com.bapidas.composesample.di.module.provide

import android.app.Application
import com.bapidas.composesample.data.db.AppDatabase
import com.bapidas.composesample.data.db.dao.NewsArticlesDao
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @Provides
    fun providesAppDatabase(application: Application): AppDatabase =
        AppDatabase.getInstance(application)

    @Provides
    fun providesNewsDao(appDatabase: AppDatabase): NewsArticlesDao =
        appDatabase.newsArticlesDao()
}