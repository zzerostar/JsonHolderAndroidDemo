package com.ziano.jsonholderandroid.jsonholder.data.model

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val errorMsg: String) : State<T>()

    fun isLoading(): Boolean = this is Loading

    fun isSuccess(): Boolean = this is Success

    fun isError(): Boolean = this is Error

    companion object {
        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) =
            Success(data)

        fun <T> error(message: String) =
            Error<T>(message)

        fun <T> fromResponse(response: NetResponse<T>): State<T> = when (response) {
            is NetResponse.Success -> success(response.data)
            is NetResponse.Failed -> error(response.errorMsg)
        }
    }
}
