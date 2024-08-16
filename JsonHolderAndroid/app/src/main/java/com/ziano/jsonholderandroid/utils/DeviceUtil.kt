package com.ziano.kotlinandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.DisplayMetrics
import com.ziano.jsonholderandroid.ZianoApplication

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
class DeviceUtil {


    companion object {
        fun isNetworkConnected(): Boolean {
            val connectivityManager = ZianoApplication.instance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }

        fun getScreenWidth(): Int {
            val displayMetrics = ZianoApplication.instance().applicationContext.resources.displayMetrics
            return displayMetrics.widthPixels

        }

        fun getScreenHeight(): Int {
            val displayMetrics = ZianoApplication.instance().applicationContext.resources.displayMetrics
            return displayMetrics.widthPixels

        }
    }
}