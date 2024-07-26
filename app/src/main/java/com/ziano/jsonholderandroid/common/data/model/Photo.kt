package com.ziano.jsonholderandroid.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
@Entity(tableName = Photo.TABLE_NAME)
data class Photo(
    val albumID: Int,
    @PrimaryKey
    val id: Int, val title: String, val url: String, val thumbnailUrl: String


) {
    companion object {
        const val TABLE_NAME = "photo"
    }
}
