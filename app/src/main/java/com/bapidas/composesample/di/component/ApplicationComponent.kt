package com.bapidas.composesample.di.component

import com.bapidas.composesample.NewsApplication
import com.bapidas.composesample.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<NewsApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): ApplicationComponent
    }
}
