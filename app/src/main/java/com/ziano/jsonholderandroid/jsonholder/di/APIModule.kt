package com.ziano.jsonholderandroid.jsonholder.di

import com.ziano.jsonholderandroid.jsonholder.data.RetrofitManager
import com.ziano.jsonholderandroid.jsonholder.data.api.PhotoService
import com.ziano.jsonholderandroid.jsonholder.data.api.PostService
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
class APIModule {

    @Provides
    @Singleton
    fun providePostService(): PostService {
        return RetrofitManager.get().create(PostService::class.java)
    }

    @Provides
    @Singleton
    fun providePhotoService(): PhotoService {
        return RetrofitManager.get().create(PhotoService::class.java)
    }
}