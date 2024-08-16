package com.ziano.jsonholderandroid.jsonholder.old.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Constructor

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
class CoreViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            for (constructor in modelClass.constructors) {
                if (arrayOf(Context::class.java).contentEquals(constructor.parameterTypes)) {
                    return (constructor as Constructor<T>).newInstance(context)
                }
            }
            return modelClass.newInstance()
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}