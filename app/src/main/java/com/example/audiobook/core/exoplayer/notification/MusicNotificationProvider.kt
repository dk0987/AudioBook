package com.example.audiobook.core.exoplayer.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaStyleNotificationHelper
import com.example.audiobook.core.di.AudioBookDispatcher
import com.example.audiobook.core.di.Dispatcher
import com.google.common.collect.ImmutableList
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MusicNotificationId = 1001
private const val MusicNotificationChannelId = "MusicNotificationChannel"

@UnstableApi
class MusicNotificationProvider @Inject constructor(
    @Dispatcher(AudioBookDispatcher.MAIN) mainDispatcher : CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    @Dispatcher(AudioBookDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : MediaNotification.Provider, Parcelable {

    private val notificationManager = checkNotNull(context.getSystemService<NotificationManager>())
    private val coroutineScope = CoroutineScope(mainDispatcher + SupervisorJob())

    constructor(parcel: Parcel) : this(
        TODO("mainDispatcher"),
        TODO("context"),
        TODO("ioDispatcher")
    ) {
    }

    override fun createNotification(
        mediaSession: MediaSession,
        customLayout: ImmutableList<CommandButton>,
        actionFactory: MediaNotification.ActionFactory,
        onNotificationChangedCallback: MediaNotification.Provider.Callback
    ): MediaNotification {
        ensureNotificationChannel()

        val player = mediaSession.player
        val metadata = player.mediaMetadata

        val builder = NotificationCompat.Builder(context, MusicNotificationChannelId)
            .setContentTitle(metadata.title)
            .setContentText(metadata.artist)
            .setSmallIcon(AppIcons.Music.resourceId)
            .setStyle(MediaStyleNotificationHelper.MediaStyle(mediaSession))

        getNotificationActions(
            mediaSession = mediaSession ,
            customLayout = customLayout ,
            actionFactory = actionFactory,
            playWhenReady = player.playWhenReady
        ).forEach(builder::addAction)

        setupArtwork(
            uri = metadata.artworkUri ,
            setLargeIcon = builder::setLargeIcon,
            updateNotification = {
                val notification = MediaNotification(MusicNotificationId, builder.build())
                onNotificationChangedCallback.onNotificationChanged(notification)
            }
        )

        return MediaNotification(MusicNotificationId, builder.build())
    }

    override fun handleCustomCommand(
        session: MediaSession,
        action: String,
        extras: Bundle
    ) = false

    fun cancelCoroutineScope() = coroutineScope.cancel()

    private fun ensureNotificationChannel(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O ||
            notificationManager.getNotificationChannel(MusicNotificationChannelId) != null
        ) {
            return
        }
        val notificationChannel = NotificationChannel(
            MusicNotificationChannelId,
            "Music Notification Channel",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun getNotificationActions(
        mediaSession: MediaSession,
        customLayout: ImmutableList<CommandButton>,
        actionFactory: MediaNotification.ActionFactory,
        playWhenReady : Boolean
    ) = listOf(
        MusicActions.getSkipPreviousAction(context, mediaSession, actionFactory),
        MusicActions.getPlayPauseAction(context, mediaSession, actionFactory, playWhenReady),
        MusicActions.getSkipNextAction(context, mediaSession, actionFactory),
    )

    private suspend fun loadArtworkBitmap(uri: Uri?) =
        withContext(ioDispatcher) { uri?.asArtworkBitmap(context) }

    private fun setupArtwork(
        uri: Uri?,
        setLargeIcon: (Bitmap?) -> Unit,
        updateNotification: () -> Unit
    ) = coroutineScope.launch {
        val bitmap = loadArtworkBitmap(uri)
        setLargeIcon(bitmap)
        updateNotification()
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<MusicNotificationProvider> {
        override fun createFromParcel(parcel: Parcel): MusicNotificationProvider {
            return MusicNotificationProvider(parcel)
        }

        override fun newArray(size: Int): Array<MusicNotificationProvider?> {
            return arrayOfNulls(size)
        }
    }

}