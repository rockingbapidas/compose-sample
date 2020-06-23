package com.bapidas.composesample.presentation.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bapidas.composesample.presentation.base.viewmodel.BaseActivityViewModel
import com.bapidas.composesample.presentation.di.qualifier.ActivityContext
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<V : BaseActivityViewModel> : AppCompatActivity(),
    HasAndroidInjector {
    protected abstract val viewModelClass: Class<V>

    protected lateinit var viewModel: V
        private set

    @Inject
    protected lateinit var supportFragmentInjector: DispatchingAndroidInjector<Any>

    @Inject
    @field:ActivityContext
    protected lateinit var viewModelProvider: ViewModelProvider

    override fun androidInjector(): AndroidInjector<Any> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //create view model
        viewModel = viewModelProvider.get(viewModelClass)
        viewModel.handleCreate()
        viewModel.handleIntent(intent)
        onViewModelCreated()

        //create view
        onViewCreated()
    }

    @CallSuper
    protected open fun onViewModelCreated() {
    }

    @CallSuper
    protected open fun onViewCreated() {
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        viewModel.handleIntent(intent)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.handleResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.handlePermissionResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.handlePause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.handleRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.handleSaveInstanceState(outState)
    }
}
