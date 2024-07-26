package com.ziano.jsonholderandroid.common.data.api

import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
interface PostService {

    @GET("/posts")
    suspend fun getPosts(@Query("_start") start: Int, @Query("_limit") limit: Int): Response<List<Post>>

    @GET("/posts/{id}")
    suspend fun getPostDetail(@Path("id") id: Int): Response<Post>

    @GET("/comments")
    suspend fun getPostComments(@Query("postId") postId: Int): Response<List<Comment>>
}