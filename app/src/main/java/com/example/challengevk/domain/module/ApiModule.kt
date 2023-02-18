package com.example.challengevk.domain.module

import com.example.challengevk.data.api.ApiService
import com.example.challengevk.data.constants.Constant.base_url
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideGsonFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .setPrettyPrinting()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(factory)
            .build()
}