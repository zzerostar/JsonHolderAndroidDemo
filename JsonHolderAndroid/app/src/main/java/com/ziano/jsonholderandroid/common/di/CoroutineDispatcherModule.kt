package com.ziano.jsonholderandroid.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/8/27
 * @desc
 */
@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}