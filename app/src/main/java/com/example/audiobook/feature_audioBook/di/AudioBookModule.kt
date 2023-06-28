package com.example.audiobook.feature_audioBook.di

import com.example.audiobook.feature_audioBook.data.remote.BookAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AudioBookModule {

    @Provides
    @Singleton
    fun provideCharacterApi() : BookAPI {
        return Retrofit.Builder()
            .baseUrl(BookAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }
}