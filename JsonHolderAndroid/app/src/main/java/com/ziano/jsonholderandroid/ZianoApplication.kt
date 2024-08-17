package com.ziano.jsonholderandroid

import android.app.Application
import com.ziano.jsonholderandroid.common.FlutterConstants.JSON_HOLDER_ENGINE_ID
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

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

        val flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute(JSON_HOLDER_ENGINE_ID)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(JSON_HOLDER_ENGINE_ID, flutterEngine)
    }

    companion object {
        private lateinit var instance: ZianoApplication
        fun instance() = instance

    }
}