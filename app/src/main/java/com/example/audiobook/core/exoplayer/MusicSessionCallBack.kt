package com.example.audiobook.core.exoplayer

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.media3.common.MediaItem
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionResult
import com.example.audiobook.core.di.AudioBookDispatcher
import com.example.audiobook.core.di.Dispatcher
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class MusicSessionCallBack @Inject constructor(
    @Dispatcher(AudioBookDispatcher.MAIN) mainDispatcher : CoroutineDispatcher ,
    private val musicActionHandler : MusicActionHandler
) : MediaLibrarySession.Callback {

    private val coroutineScope = CoroutineScope(mainDispatcher + SupervisorJob())

    override fun onAddMediaItems(
        mediaSession: MediaSession,
        controller: MediaSession.ControllerInfo,
        mediaItems: MutableList<MediaItem>
    ): ListenableFuture<List<MediaItem>> = Futures.immediateFuture(
        mediaItems.map { mediaItem ->  
            mediaItem.buildUpon()
                .setUri(mediaItem.requestMetadata.mediaUri)
                .build()
        }
    )

    override fun onConnect(
        session: MediaSession,
        controller: MediaSession.ControllerInfo
    ): MediaSession.ConnectionResult {
        val connectionResult = super.onConnect(session, controller)
        val availableSessionCommand = connectionResult.availableSessionCommands.buildUpon()
        musicActionHandler.customCommands.values.forEach {commandButton ->
            commandButton.sessionCommand?.let(availableSessionCommand::add)
        }

        return MediaSession.ConnectionResult.accept(
            availableSessionCommand.build() ,
            connectionResult.availablePlayerCommands
        )
    }

    override fun onPostConnect(session: MediaSession, controller: MediaSession.ControllerInfo) {
        session.setCustomLayout(controller , musicActionHandler.customLayout)
    }

    override fun onCustomCommand(
        session: MediaSession,
        controller: MediaSession.ControllerInfo,
        customCommand: SessionCommand,
        args: Bundle
    ): ListenableFuture<SessionResult> {
        musicActionHandler.onCustomCommand(session, customCommand )
        session.setCustomLayout(musicActionHandler.customLayout)
        return Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
    }

    fun cancelCoroutineScope() {
        coroutineScope.cancel()
        musicActionHandler.cancelCoroutineScope()
    }
}