package com.example.challengevk.domain.module

import com.example.challengevk.data.repository.VkServiceRepository
import com.example.challengevk.domain.repository.VkServiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVkServiceRepository(
        vkServiceRepositoryImpl: VkServiceRepositoryImpl
    ): VkServiceRepository
}