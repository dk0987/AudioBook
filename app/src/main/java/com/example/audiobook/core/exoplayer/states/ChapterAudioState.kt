package com.example.audiobook.core.exoplayer.states

import com.example.audiobook.core.domain.modal.Chapter

data class ChapterAudioState(
    val chapter : Chapter? = null ,
    val playbackState: PlaybackState = PlaybackState.IDLE,
    val playWhenReady: Boolean = false,
    val duration: Long = 0,
    val chapters : List<Chapter> = emptyList()
)
