package com.ziano.jsonholderandroid.jsonholder.data.api

import com.ziano.jsonholderandroid.jsonholder.data.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
interface PhotoService {

    @GET("albums/{albumId}/photos")
    suspend fun getPhotos(@Path("albumId") albumId: Int): Response<List<Photo>>
}