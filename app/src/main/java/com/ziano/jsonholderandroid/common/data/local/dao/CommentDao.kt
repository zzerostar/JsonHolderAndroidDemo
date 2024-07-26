package com.ziano.jsonholderandroid.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ziano.jsonholderandroid.common.data.model.Comment

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/28
 * @desc
 */
@Dao
interface CommentDao {

    @Query("SELECT * FROM ${Comment.TABLE_NAME} WHERE postId = :postId")
    suspend fun queryByPostId(postId: Int): List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<Comment>)

}