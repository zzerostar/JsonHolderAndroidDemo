package com.ziano.jsonholderandroid.jsonholder.old.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.ziano.jsonholderandroid.R
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/23
 * @desc
 */
abstract class BaseBindingActivity<VM : ViewModel, VB : ViewBinding>(private val inflate: (LayoutInflater) -> VB) : AppCompatActivity() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }

    protected fun showLoading() {
        val loadingView: View? = findViewById<View?>(R.id.common_loading_layout)
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
        val loadingView: View? = findViewById<View?>(R.id.common_loading_layout)
        loadingView?.apply {
            visibility = View.GONE
        }
    }

    protected fun showError(errorMsg: String = "", onRetry: () -> Unit = ::fetchData) {
        val loadingView: View? = findViewById<View?>(R.id.common_loading_layout)
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
            Log.e(BaseBindingActivity::class.simpleName, e.toString())
            e.printStackTrace()
        }
    }

}