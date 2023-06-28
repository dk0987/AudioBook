package com.example.audiobook.core.exoplayer.util

import androidx.media3.common.Player
import androidx.media3.common.C
import com.example.audiobook.core.exoplayer.states.PlaybackState

internal fun Int.asPlaybackState() = when (this) {
    Player.STATE_IDLE -> PlaybackState.IDLE
    Player.STATE_BUFFERING -> PlaybackState.BUFFERING
    Player.STATE_READY -> PlaybackState.READY
    Player.STATE_ENDED -> PlaybackState.ENDED
    else -> error("Invalid playback state error")
}

internal fun Long.orDefaultTimestamp() = takeIf { it != C.TIME_UNSET } ?: 0