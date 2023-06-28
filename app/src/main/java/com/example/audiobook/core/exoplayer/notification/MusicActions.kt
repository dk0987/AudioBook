package com.example.audiobook.core.exoplayer.notification

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal object MusicActions {

    internal fun getPlayPauseAction(
        context: Context,
        mediaSession: MediaSession,
        actionFactory: MediaNotification.ActionFactory,
        playWhenReady: Boolean
    ) = MusicAction(
        iconResource = if (playWhenReady) AppIcons.Pause.resourceId else AppIcons.Play.resourceId,
        titleResource = if (playWhenReady) "Pause" else "Play",
        command = Player.COMMAND_PLAY_PAUSE
    ).asNotificationAction(context, mediaSession, actionFactory)


    internal fun getSkipPreviousAction(
        context: Context,
        mediaSession: MediaSession,
        actionFactory: MediaNotification.ActionFactory
    ) = MusicAction(
        iconResource = AppIcons.SkipPrevious.resourceId,
        titleResource = "Prev",
        command = Player.COMMAND_SEEK_TO_PREVIOUS
    ).asNotificationAction(context, mediaSession, actionFactory)


    internal fun getSkipNextAction(
        context: Context,
        mediaSession: MediaSession,
        actionFactory: MediaNotification.ActionFactory
    ) = MusicAction(
        iconResource = AppIcons.SkipNext.resourceId,
        titleResource = "Next",
        command = Player.COMMAND_SEEK_TO_NEXT
    ).asNotificationAction(context, mediaSession, actionFactory)


}