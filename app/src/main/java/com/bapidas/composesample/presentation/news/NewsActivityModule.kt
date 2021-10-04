package com.bapidas.composesample.presentation.news

import androidx.appcompat.app.AppCompatActivity
import com.bapidas.composesample.presentation.base.activity.BaseActivityModule
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.key.ActivityViewModelKey
import com.bapidas.composesample.presentation.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class])
abstract class NewsActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: NewsActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(NewsViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(viewModel: NewsViewModel): BaseActivityViewModel
}