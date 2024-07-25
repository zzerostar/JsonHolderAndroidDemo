package com.ziano.jsonholderandroid.jsonholder.data.repositroy

import android.util.Log
import com.ziano.kotlinandroid.utils.DeviceUtil
import com.ziano.jsonholderandroid.jsonholder.data.model.NetResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
abstract class BaseRepository {

    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        run {
            Log.e("BaseRepository", e.toString())
        }
    }

    protected suspend fun <T> request(netRequest: NetRequest<T>): Flow<NetResponse<T>> {

        return flow<NetResponse<T>> {

            if(!DeviceUtil.isNetworkConnected()) {
                emit(NetResponse.Success<T>(netRequest.getFromLocal()))
                return@flow
            }

            val response = netRequest.fetchFromRemote()

            val remoteData = response.body()

            if(response.isSuccessful && remoteData != null) {
                netRequest.cacheRemoteData(remoteData)
                emit(NetResponse.Success(netRequest.getFromLocal()))

            } else {
                emit(NetResponse.Failed<T>(response.message()))
            }

        }.flowOn(defaultDispatcher).catch { e ->
            Log.e("BaseRepository", e.toString())
            e.printStackTrace()
            emit(NetResponse.Failed<T>("Network error"))
        }
    }

}