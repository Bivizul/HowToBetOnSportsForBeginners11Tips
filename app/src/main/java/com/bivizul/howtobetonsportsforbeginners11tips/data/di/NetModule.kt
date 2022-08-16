package com.bivizul.howtobetonsportsforbeginners11tips.data.di

import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.BASE_URL
import com.bivizul.howtobetonsportsforbeginners11tips.data.NetService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Provides
    fun provideBase() = BASE_URL

    @Provides
    @Singleton
    fun provideNetwork(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetService::class.java)

}