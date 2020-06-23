package com.bapidas.composesample.presentation.base.viewmodel.factory

import androidx.lifecycle.ViewModel
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.scope.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ActivityViewModelFactory @Inject constructor(
    creators: Map<Class<out BaseActivityViewModel>,
            @JvmSuppressWildcards Provider<BaseActivityViewModel>>
) : BaseViewModelFactory(creators as Map<Class<out ViewModel>, Provider<ViewModel>>)