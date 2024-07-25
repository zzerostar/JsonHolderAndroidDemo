package com.ziano.jsonholderandroid.jsonholder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ziano.jsonholderandroid.jsonholder.data.model.Photo

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
@Dao
interface PhotoDao {

    @Query("select * from ${Photo.TABLE_NAME}")
    suspend fun queryAll(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(posts:List<Photo>)
}