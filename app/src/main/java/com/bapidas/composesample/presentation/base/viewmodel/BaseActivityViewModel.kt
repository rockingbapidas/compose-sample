package com.bapidas.composesample.presentation.base.viewmodel

import android.content.Intent

abstract class BaseActivityViewModel : BaseViewModel() {
    open fun handleIntent(intent: Intent) {}
}