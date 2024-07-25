package com.ziano.jsonholderandroid.jsonholder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/28
 * @desc
 */
@Entity(tableName = Comment.TABLE_NAME)
data class Comment(
    @PrimaryKey
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String) {

    companion object {
        const val TABLE_NAME="comment"
    }
}