package com.ziano.jsonholderandroid.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ziano.jsonholderandroid.common.data.local.dao.CommentDao
import com.ziano.jsonholderandroid.common.data.local.dao.PhotoDao
import com.ziano.jsonholderandroid.common.data.local.dao.PostDao
import com.ziano.jsonholderandroid.common.data.local.dao.UserDao
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.Photo
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.model.User

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@Database(entities = [Post::class, Comment::class, Photo::class, User::class], version = 2)
abstract class JsonHolderDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getCommentDao(): CommentDao
    abstract fun getPhotoDao(): PhotoDao

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: JsonHolderDatabase? = null

        private val DB_NAME = "json_holder_database";

        final val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS user (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `address` TEXT NOT NULL, `phone` TEXT NOT NULL, `website` TEXT NOT NULL, `company` TEXT NOT NULL, PRIMARY KEY(`id`))")
            }
        }

        fun getInstance(context: Context): JsonHolderDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, JsonHolderDatabase::class.java, DB_NAME).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance

            }

        }
    }

}