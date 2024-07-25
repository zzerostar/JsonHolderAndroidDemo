//package com.ziano.zianojetpackcompose.jsonholder.vm
//
//import com.ziano.zianojetpackcompose.base.IViewState
//import com.ziano.zianojetpackcompose.jsonholder.data.model.ListViewState
//import com.ziano.zianojetpackcompose.jsonholder.data.model.ListViewStatus
//import com.ziano.zianojetpackcompose.jsonholder.data.model.NetResponse
//import com.ziano.zianojetpackcompose.jsonholder.data.model.Post
//
///**
// * @author zz
// * @email zzerostar@163.com
// * @date 2024/7/23
// * @desc
// */
//class PostListViewState(
//    val status: ListViewStatus, val data: List<Post> = mutableListOf(), val errorMsg: String = ""
//) : IViewState {
//
//    fun fromNetResponse(netResponse: NetResponse<List<Post>>, loadMore: Boolean = false): PostListViewState {
//        when (netResponse) {
//            is NetResponse.Failed -> {
//
//                return PostListViewState(ListViewStatus.error, errorMsg = netResponse.errorMsg)
//            }
//
//            is NetResponse.Success -> {
//                if (loadMore) {
//                    return PostListViewState(ListViewStatus.success, data + netResponse.data)
//                } else {
//                    return PostListViewState(ListViewStatus.success, netResponse.data)
//                }
//            }
//        }
//    }
//}