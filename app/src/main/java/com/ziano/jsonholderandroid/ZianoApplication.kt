package com.ziano.jsonholderandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@HiltAndroidApp
class ZianoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: ZianoApplication
        fun instance() = instance

    }
}