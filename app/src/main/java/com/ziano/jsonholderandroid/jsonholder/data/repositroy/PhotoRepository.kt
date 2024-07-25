package com.ziano.jsonholderandroid.jsonholder.data.repositroy

import com.ziano.jsonholderandroid.jsonholder.data.api.PhotoService
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.PhotoDao
import com.ziano.jsonholderandroid.jsonholder.data.model.NetResponse
import com.ziano.jsonholderandroid.jsonholder.data.model.Photo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
class PhotoRepository @Inject constructor(val photoAPI: PhotoService, val photoDao: PhotoDao) : BaseRepository() {

    suspend fun getPhotoList(albumId: Int): Flow<NetResponse<List<Photo>>> {
        return request(object : NetRequest<List<Photo>> {
            override suspend fun fetchFromRemote(): Response<List<Photo>> {
                return photoAPI.getPhotos(albumId)
            }

            override suspend fun getFromLocal(): List<Photo> {
                return photoDao.queryAll()
            }

            override suspend fun cacheRemoteData(data: List<Photo>) {
                photoDao.insertPhotos(data)
            }

        })
    }

}