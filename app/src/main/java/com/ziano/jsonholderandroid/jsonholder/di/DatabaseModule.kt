package com.ziano.jsonholderandroid.jsonholder.di

import android.app.Application
import com.ziano.jsonholderandroid.jsonholder.data.local.JsonHolderDatabase
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.CommentDao
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.PhotoDao
import com.ziano.jsonholderandroid.jsonholder.data.local.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/24
 * @desc
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): JsonHolderDatabase = JsonHolderDatabase.getInstance(application)

    @Singleton
    @Provides
    fun getPostDao(jsonHolderDatabase: JsonHolderDatabase): PostDao = jsonHolderDatabase.getPostDao()

    @Singleton
    @Provides
    fun getCommentDao(jsonHolderDatabase: JsonHolderDatabase): CommentDao = jsonHolderDatabase.getCommentDao()

    @Singleton
    @Provides
    fun getPhotoDao(jsonHolderDatabase: JsonHolderDatabase): PhotoDao = jsonHolderDatabase.getPhotoDao()

//    @Singleton
//    @Provides
//    fun getUserDao(jsonHolderDatabase: JsonHolderDatabase) : UserDao = jsonHolderDatabase.getUserDao()
}