package com.example.audiobook.core.exoplayer.notification

import android.content.Context
import androidx.core.graphics.drawable.IconCompat
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal fun MusicAction.asNotificationAction(
    context : Context ,
    mediaSession: MediaSession ,
    actionFactory : MediaNotification.ActionFactory
) = actionFactory.createMediaAction(
    mediaSession,
    IconCompat.createWithResource(context , iconResource),
    titleResource,
    command
)