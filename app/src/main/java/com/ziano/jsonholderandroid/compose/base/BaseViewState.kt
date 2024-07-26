package com.ziano.jsonholderandroid.compose.base

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/25
 * @desc
 */
abstract class BaseViewState<T>(
    open val status: ViewStatus = ViewStatus.loading,
    open val data: T? = null,
    open val errorMsg: String = ""
) : IViewState {

    abstract fun copy(status: ViewStatus = ViewStatus.loading, data: T? = null, errorMsg: String = ""): BaseViewState<T>
}