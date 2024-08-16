package com.ziano.jsonholderandroid.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ziano.jsonholderandroid.common.data.model.User

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
@Dao
interface UserDao {

    @Query("select * from ${User.TABLE_NAME}")
    suspend fun queryAll() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users : List<User>)
}