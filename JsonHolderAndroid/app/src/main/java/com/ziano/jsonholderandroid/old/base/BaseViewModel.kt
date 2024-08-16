package com.ziano.jsonholderandroid.jsonholder.old.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/28
 * @desc
 */
open class BaseViewModel : ViewModel() {

    protected val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        run {
            Log.e(BaseViewModel::class.simpleName, e.toString())
        }
    }

    protected val ioDispatcherContext = exceptionHandler + Dispatchers.IO
}