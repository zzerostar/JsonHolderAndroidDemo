package com.ziano.jsonholderandroid.common.data.api

import com.ziano.jsonholderandroid.common.data.model.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
interface UserService {
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>
}