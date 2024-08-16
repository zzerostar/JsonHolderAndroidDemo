package com.ziano.jsonholderandroid.compose.base

import com.ziano.jsonholderandroid.common.data.model.NetResponse

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/24
 * @desc
 */

enum class ViewStatus {
    loading, success, error
}

abstract class CommonViewState<T, S : BaseViewState<T>>(
    override val status: ViewStatus = ViewStatus.loading,
    override val data: T? = null,
    override val errorMsg: String = ""
) : BaseViewState<T>() {

    abstract fun copyImpl(status: ViewStatus, data: T? = null, errorMsg: String): S

    override fun copy(status: ViewStatus, data: T?, errorMsg: String): S {
        return copyImpl(status, data, errorMsg)
    }

    fun fromNetResponse(response: NetResponse<T>): S = when (response) {
        is NetResponse.Success -> {
            copy(status = ViewStatus.success, data = response.data)
        }

        is NetResponse.Failed -> {
            copy(status = ViewStatus.error, errorMsg = response.errorMsg)
        }
    }

}
