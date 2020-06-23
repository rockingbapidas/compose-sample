package com.bapidas.composesample.presentation.base.viewmodel.factory

import androidx.lifecycle.ViewModel
import com.bapidas.composesample.presentation.base.viewmodel.BaseFragmentViewModel
import com.bapidas.composesample.presentation.di.scope.FragmentScope
import javax.inject.Inject
import javax.inject.Provider

@FragmentScope
class FragmentViewModelFactory @Inject constructor(
    creators: Map<Class<out BaseFragmentViewModel>,
            @JvmSuppressWildcards Provider<BaseFragmentViewModel>>
) :
    BaseViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)