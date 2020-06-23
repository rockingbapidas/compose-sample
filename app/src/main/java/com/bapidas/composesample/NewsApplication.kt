package com.bapidas.composesample

import com.bapidas.composesample.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class NewsApplication : DaggerApplication() {
    @Inject
    lateinit var timberTree: Timber.Tree

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(timberTree)
    }
}