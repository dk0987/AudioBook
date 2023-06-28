package com.example.audiobook.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val audioBookDispatcher : AudioBookDispatcher)

enum class AudioBookDispatcher { DEFAULT, MAIN, UNCONFINED, IO }