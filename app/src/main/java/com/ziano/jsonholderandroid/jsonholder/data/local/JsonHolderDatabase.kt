package com.ziano.jsonholderandroid.jsonholder.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.CommentDao
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.PhotoDao
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.PostDao
import com.ziano.jsonholderandroid.jsonholder.data.model.Comment
import com.ziano.jsonholderandroid.jsonholder.data.model.Photo
import com.ziano.jsonholderandroid.jsonholder.data.model.Post

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@Database(entities = [Post::class, Comment::class, Photo::class], version = 1)
abstract class JsonHolderDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getCommentDao(): CommentDao
    abstract fun getPhotoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: JsonHolderDatabase? = null

        private val DB_NAME = "json_holder_database";

        fun getInstance(context: Context): JsonHolderDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, JsonHolderDatabase::class.java, DB_NAME).build()
                INSTANCE = instance
                return instance

            }

        }
    }

}