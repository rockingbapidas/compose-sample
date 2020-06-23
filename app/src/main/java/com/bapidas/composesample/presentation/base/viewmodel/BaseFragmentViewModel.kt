package com.bapidas.composesample.presentation.base.viewmodel

import android.os.Bundle

abstract class BaseFragmentViewModel : BaseViewModel() {
    open fun handleArguments(bundle: Bundle?) {}
}