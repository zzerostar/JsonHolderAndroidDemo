package com.ziano.jsonholderandroid.old.vm

import com.ziano.jsonholderandroid.common.data.model.NetResponse

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/26
 * @desc
 */
sealed class ListViewState<T> {
    class Loading<T> : ListViewState<T>()

    data class Success<T>(val data: T) : ListViewState<T>()

    data class Error<T>(val errorMsg: String) : ListViewState<T>()

    data class LoadMore<T>(val data: T) : ListViewState<T>()

    fun isLoading(): Boolean = this is Loading

    fun isSuccess(): Boolean = this is Success

    fun isLoadMore(): Boolean = this is LoadMore

    fun isError(): Boolean = this is Error

    companion object {
        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) = Success(data)

        fun <T> loadMore(data: T) = LoadMore(data)

        fun <T> error(message: String) = Error<T>(message)

        fun <T> fromResponse(response: NetResponse<T>, loadMore: Boolean = false): ListViewState<T> = when (response) {
            is NetResponse.Success -> if (loadMore) loadMore(response.data) else success(response.data)
            is NetResponse.Failed -> error(response.errorMsg)
        }
    }
}