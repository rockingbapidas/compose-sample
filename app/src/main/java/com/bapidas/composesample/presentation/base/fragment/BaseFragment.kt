package com.bapidas.composesample.presentation.base.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bapidas.composesample.presentation.base.viewmodel.BaseFragmentViewModel
import com.bapidas.composesample.presentation.di.qualifier.FragmentContext
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<V : BaseFragmentViewModel> : Fragment(),
    HasAndroidInjector {
    protected abstract val viewModelClass: Class<V>

    protected lateinit var viewModel: V
        private set

    @Inject
    protected lateinit var childFragmentInjector: DispatchingAndroidInjector<Any>

    @Inject
    @field:FragmentContext
    protected lateinit var viewModelProvider: ViewModelProvider

    override fun androidInjector(): AndroidInjector<Any> {
        return childFragmentInjector
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create view model
        viewModel = viewModelProvider.get(viewModelClass)
        viewModel.handleCreate()
        viewModel.handleArguments(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frameLayout = FrameLayout(requireContext())
        buildUi(frameLayout)
        return frameLayout
    }

    protected abstract fun buildUi(view: View)

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.handleResult(requestCode, resultCode, data)
    }

    @CallSuper
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { viewModel.handleRestoreInstanceState(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.handleSaveInstanceState(outState)
    }
}