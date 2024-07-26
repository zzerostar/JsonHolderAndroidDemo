package com.ziano.jsonholderandroid.common.data.repositroy

import com.ziano.jsonholderandroid.common.data.api.UserService
import com.ziano.jsonholderandroid.common.data.local.dao.UserDao
import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
class UserRepository @Inject constructor(val userDao: UserDao, val userAPI: UserService) : BaseRepository() {
    suspend fun getUser(): Flow<NetResponse<List<User>>> {
        return request(object : NetRequest<List<User>> {
            override suspend fun fetchFromRemote(): Response<List<User>> {

                return userAPI.getUsers()
            }

            override suspend fun getFromLocal(): List<User> {
                return userDao.queryAll()
            }

            override suspend fun cacheRemoteData(data: List<User>) {
                userDao.insertUsers(data)
            }

        })
    }
}