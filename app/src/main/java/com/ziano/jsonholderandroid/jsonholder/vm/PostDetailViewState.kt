package com.ziano.jsonholderandroid.jsonholder.vm

import com.ziano.jsonholderandroid.base.IViewState
import com.ziano.jsonholderandroid.base.ViewStatus
import com.ziano.jsonholderandroid.jsonholder.data.model.Comment
import com.ziano.jsonholderandroid.jsonholder.data.model.Post

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/24
 * @desc
 */
data class PostDetailViewState(val status: ViewStatus, val detail: Post? = null, val comments: List<Comment> = listOf(), val errorMsg: String = "") : IViewState {

}