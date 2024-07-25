package com.ziano.jsonholderandroid.jsonholder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
@Entity(tableName = Post.TABLE_NAME)
data class Post(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    @SerializedName("body")
    val content: String,

) {
    companion object {
        const val TABLE_NAME = "post"
    }
}
