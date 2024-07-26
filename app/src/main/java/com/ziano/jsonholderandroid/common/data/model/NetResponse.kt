package com.ziano.jsonholderandroid.common.data.model

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
sealed class NetResponse<T> {
    class Success<T>(val data: T) : NetResponse<T>()
    class Failed<T>(val errorMsg: String) : NetResponse<T>()
}