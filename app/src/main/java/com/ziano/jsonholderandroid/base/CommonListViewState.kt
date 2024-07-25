package com.ziano.jsonholderandroid.base

import com.ziano.jsonholderandroid.jsonholder.data.model.NetResponse

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/25
 * @desc
 */
abstract class CommonListViewState<T, S : CommonListViewState<T, S>>(
    override val status: ListViewStatus = ListViewStatus.refreshing,
    override val data: List<T> = mutableListOf(),
    override val errorMsg: String = ""
) : BaseListViewState<T>() {


    fun fromNetResponse(netResponse: NetResponse<List<T>>, loadMore: Boolean = false): S = when (netResponse) {
        is NetResponse.Failed -> {
            copyImpl(ListViewStatus.error, errorMsg = netResponse.errorMsg)
        }

        is NetResponse.Success -> {
            if (loadMore) {
                copyImpl(ListViewStatus.success, data + netResponse.data)
            } else {
                copyImpl(ListViewStatus.success, netResponse.data)
            }
        }
    }

    protected abstract fun copyImpl(status: ListViewStatus, data: List<T> = listOf(), errorMsg: String = ""): S

    override fun copy(status: ListViewStatus, data: List<T>, errorMsg: String): S {
        return copyImpl(status = status, data = data, errorMsg = errorMsg)
    }

}