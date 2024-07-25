package com.ziano.jsonholderandroid.jsonholder.data.repositroy

import retrofit2.Response

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/28
 * @desc
 */
interface NetRequest<T> {

    suspend fun fetchFromRemote(): Response<T>

    suspend fun cacheRemoteData(data: T)

    suspend fun getFromLocal(): T

}