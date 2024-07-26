package com.ziano.jsonholderandroid.jsonholder.old.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ziano.jsonholderandroid.R
import kotlinx.coroutines.CoroutineExceptionHandler
import java.lang.reflect.ParameterizedType

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/30
 * @desc
 */
abstract class BaseBindingFragment<VM : ViewModel, VB : ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB) : Fragment() {

    private var _mViewBinding: VB? = null
    val mViewBinding: VB get() = _mViewBinding!!

    protected abstract val mViewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _mViewBinding = inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mViewBinding = null
    }

    protected open fun getViewModel(): VM {
        val type = javaClass.genericSuperclass

        if (type != null && type is ParameterizedType) {

            val actualTypeArguments = type.actualTypeArguments
            val tClass = actualTypeArguments[0]
            return ViewModelProvider(this, CoreViewModelFactory(requireContext())).get(tClass as Class<VM>)
        }
        throw KotlinNullPointerException("ViewModel Type is not exist")
    }

    protected fun showLoading() {
        val loadingView: View? = requireView().findViewById<View?>(R.id.common_loading_layout)
        loadingView?.apply {
            visibility = View.VISIBLE
            val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
            progressbar.visibility = View.VISIBLE

            val tvErrorMsg = findViewById<TextView>(R.id.tv_error_msg)
            tvErrorMsg.visibility = View.GONE

            val btnRetry = findViewById<Button>(R.id.btn_retry)
            btnRetry.visibility = View.GONE
        }
    }

    protected abstract fun fetchData()

    protected fun endLoading() {
        val loadingView: View? = requireView().findViewById<View?>(R.id.common_loading_layout)
        loadingView?.apply {
            visibility = View.GONE
        }
    }

    protected fun showError(errorMsg: String = "", onRetry: () -> Unit = ::fetchData) {
        val loadingView: View? = requireView().findViewById<View?>(R.id.common_loading_layout)
        loadingView?.apply {
            visibility = View.VISIBLE
            val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
            progressbar.visibility = View.GONE

            val tvErrorMsg = findViewById<TextView>(R.id.tv_error_msg)
            tvErrorMsg.visibility = View.VISIBLE
            tvErrorMsg.setText(errorMsg)

            val btnRetry = findViewById<Button>(R.id.btn_retry)
            btnRetry.visibility = View.VISIBLE
            btnRetry.setOnClickListener { v -> onRetry() }
        }
    }


    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        run {
            Log.e(BaseBindingFragment::class.simpleName, e.toString())
        }
    }

}