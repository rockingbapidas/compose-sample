package com.bapidas.composesample.presentation.base.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bapidas.composesample.presentation.base.viewmodel.factory.FragmentViewModelFactory
import com.bapidas.composesample.presentation.di.qualifier.FragmentContext
import com.bapidas.composesample.presentation.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class BaseFragmentModule {
    @Binds
    @FragmentScope
    @FragmentContext
    abstract fun bindViewModelFactory(
        viewModelFactory: FragmentViewModelFactory
    ): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @FragmentScope
        @FragmentContext
        @JvmStatic
        fun provideViewModelProvider(
            fragment: Fragment,
            @FragmentContext viewModelFactory: ViewModelProvider.Factory
        ): ViewModelProvider = ViewModelProvider(fragment, viewModelFactory)
    }
}
