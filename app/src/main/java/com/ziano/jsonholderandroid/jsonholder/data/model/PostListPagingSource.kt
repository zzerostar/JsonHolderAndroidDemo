//package com.ziano.zianojetpackcompose.jsonholder.data.model
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingSource.LoadResult.Error
//import androidx.paging.PagingState
//import com.ziano.zianojetpackcompose.jsonholder.data.repositroy.PostRepository
//
///**
// * @author zz
// * @email zzerostar@163.com
// * @date 2024/6/30
// * @desc
// */
//class PostListPagingSource(private val postRepository: PostRepository) : PagingSource<Int, Post>() {
//    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
//
//        try {
//            var page = 1;
//            params.key?.apply {
//                page = params.key!!
//            }
//            Log.d("zz", "请求第${page}页")
//
//            val response = postRepository.getPostList(page);
//            if (response.isSuccessful) {
//                val posts = response.body()!!;
//                val prevKey = if (page == 1) null else page - 1
//                val nextKey = if (posts.isNotEmpty()) page + 1 else null
//
//                return LoadResult.Page(data = posts, prevKey = prevKey, nextKey = nextKey)
//
//            } else {
//                return LoadResult.Invalid()
//            }
//        } catch (e: Exception) {
//            return Error(throwable = e)
//        }
//
//
//    }
//}