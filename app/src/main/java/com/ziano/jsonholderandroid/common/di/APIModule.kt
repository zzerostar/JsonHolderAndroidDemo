package com.ziano.jsonholderandroid.common.di

import com.ziano.jsonholderandroid.common.data.RetrofitManager
import com.ziano.jsonholderandroid.common.data.api.PhotoService
import com.ziano.jsonholderandroid.common.data.api.PostService
import com.ziano.jsonholderandroid.common.data.api.UserService
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

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return RetrofitManager.get().create(UserService::class.java)
    }
}