package com.ziano.jsonholderandroid.jsonholder.data.local.dao

import androidx.room.*
import com.ziano.jsonholderandroid.jsonholder.data.model.Post

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@Dao
interface PostDao {

    @Query("select * from ${Post.TABLE_NAME}")
    suspend fun queryAll(): List<Post>
    @Query("select * from ${Post.TABLE_NAME} where id > :idStart AND id <= :idEnd")
    suspend fun queryByIdRange(idStart : Int, idEnd: Int):List<Post>

    @Query("select * from ${Post.TABLE_NAME} where id = :id")
    suspend fun queryPostById(id : Int):Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts:List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Delete
    suspend fun delete(post : Post) : Int

    @Update
    suspend fun update(post : Post) : Int
}