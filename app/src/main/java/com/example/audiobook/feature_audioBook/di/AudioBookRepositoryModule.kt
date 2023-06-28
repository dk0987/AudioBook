package com.example.audiobook.feature_audioBook.di

import com.example.audiobook.feature_audioBook.data.repository.BookRepositoryImpl
import com.example.audiobook.feature_audioBook.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AudioBookRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ) : BookRepository
}