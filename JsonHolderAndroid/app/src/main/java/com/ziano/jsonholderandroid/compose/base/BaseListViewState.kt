package com.ziano.jsonholderandroid.compose.base

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/24
 * @desc
 */

enum class ListViewStatus {
    refreshing, appending, success, error
}

abstract class BaseListViewState<T>(open val status: ListViewStatus = ListViewStatus.refreshing,
                                    open val data: List<T> = listOf(),
                                    open val errorMsg: String = "") : IViewState {
    abstract fun copy(
        status: ListViewStatus = ListViewStatus.refreshing,
        data: List<T> = this.data,
        errorMsg: String = this.errorMsg
    ): BaseListViewState<T>
}