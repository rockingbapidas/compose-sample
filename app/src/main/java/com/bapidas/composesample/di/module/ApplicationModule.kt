package com.bapidas.composesample.di.module

import android.app.Application
import android.content.Context
import com.bapidas.composesample.NewsApplication
import com.bapidas.composesample.di.module.provide.ApiModule
import com.bapidas.composesample.di.module.provide.NetworkModule
import com.bapidas.composesample.di.module.provide.RoomModule
import com.bapidas.composesample.di.module.provide.TimberModule
import com.bapidas.composesample.di.qualifier.ApplicationContext
import com.bapidas.composesample.presentation.di.ActivityBuilderModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(
    includes = [AndroidInjectionModule::class,
        TimberModule::class,
        RoomModule::class,
        NetworkModule::class,
        ApiModule::class,
        ActivityBuilderModule::class
    ]
)
abstract class ApplicationModule {
    @Binds
    abstract fun bindApplication(application: NewsApplication): Application

    @Binds
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context
}
