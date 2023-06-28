package com.example.audiobook.core.exoplayer.notification

import androidx.annotation.DrawableRes
import androidx.media3.common.Player

    internal data class MusicAction(
        @DrawableRes val iconResource: Int,
        val titleResource: String,
        @Player.Command val command: Int
    )
