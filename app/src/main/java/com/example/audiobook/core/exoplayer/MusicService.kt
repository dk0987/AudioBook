package com.example.audiobook.core.exoplayer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import com.example.audiobook.core.exoplayer.notification.MusicNotificationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.media3.common.C.AUDIO_CONTENT_TYPE_MUSIC
import androidx.media3.exoplayer.ExoPlayer

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class MusicService  : MediaLibraryService(){

    private var mediaLibrarySession : MediaLibrarySession? = null

    @Inject
    lateinit var musicSessionCallback : MusicSessionCallBack

    @Inject
    lateinit var musicNotificationProvider : MusicNotificationProvider

    override fun onCreate() {
        super.onCreate()

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

        val player = ExoPlayer.Builder(this)
            .setAudioAttributes(audioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()

        mediaLibrarySession =
            MediaLibrarySession.Builder(this, player, musicSessionCallback).build()

        setMediaNotificationProvider(musicNotificationProvider)

    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo) = mediaLibrarySession

    override fun onDestroy() {
        super.onDestroy()
        mediaLibrarySession?.run {
            player.release()
            release()
            mediaLibrarySession = null
        }
        musicSessionCallback.cancelCoroutineScope()
        musicNotificationProvider.cancelCoroutineScope()
    }

}