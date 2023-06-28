package com.example.audiobook.core.exoplayer

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.media3.common.Player
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.SessionCommand
import com.example.audiobook.core.di.AudioBookDispatcher
import com.example.audiobook.core.di.Dispatcher
import com.example.audiobook.core.exoplayer.notification.AppIcons
import com.example.audiobook.core.exoplayer.notification.NEXT
import com.example.audiobook.core.exoplayer.notification.PAUSE
import com.example.audiobook.core.exoplayer.notification.PLAY
import com.example.audiobook.core.exoplayer.notification.PLAY_PAUSE
import com.example.audiobook.core.exoplayer.notification.PREV
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class MusicActionHandler @Inject constructor(
    @Dispatcher(AudioBookDispatcher.MAIN) mainDispatcher: CoroutineDispatcher ,
    @ApplicationContext private val context : Context
) {

    private val coroutineScope = CoroutineScope(mainDispatcher + SupervisorJob())

    private val customLayoutMap = mutableMapOf<String , CommandButton>()
    val customLayout : List<CommandButton> get() = customLayoutMap.values.toList()
    val customCommands = getAvailableCustomCommand()

    init {
        loadCustomLayout()
    }


    fun onCustomCommand(mediaSession: MediaSession , customCommand : SessionCommand) =  when(customCommand.customAction){
        PLAY , PAUSE , NEXT , PREV -> {
            handleCommand(
                action = customCommand.customAction ,
                player = mediaSession.player
            )
        }
        else -> {
            error("Unknown Action Performed")
        }
    }

    fun cancelCoroutineScope() = coroutineScope.cancel()

    private fun handleCommand(action : String , player : Player ) = when(action)  {
        PLAY -> {
            player.play()
        }
        PAUSE -> {
            player.pause()
        }
        NEXT -> {
            player.seekToNext()
            player.play()
        }
        PREV -> {
            player.seekToPrevious()
            player.play()
        }

        else -> { error("Unknown error occurred") }
    }

    private fun loadCustomLayout() = customLayoutMap.run {
        this[PLAY_PAUSE] = customCommands.getValue(PLAY)
    }

    private fun getAvailableCustomCommand() = mapOf(
         PLAY to buildCustomCommand(
            action = PLAY ,
            displayName = PLAY,
            iconResource = AppIcons.Play.resourceId
        ),
        PAUSE to buildCustomCommand(
            action = PAUSE,
            displayName = PAUSE,
            iconResource = AppIcons.Pause.resourceId
        ),
        NEXT to buildCustomCommand(
            action = NEXT,
            displayName = NEXT,
            iconResource = AppIcons.SkipNext.resourceId
        ),
        PREV to buildCustomCommand(
            action = PREV,
            displayName = PREV,
            iconResource = AppIcons.SkipPrevious.resourceId
        )
    )

    private fun buildCustomCommand(
        action : String ,
        displayName : String ,
        @DrawableRes iconResource : Int
    ) = CommandButton.Builder()
        .setSessionCommand(SessionCommand(action , Bundle.EMPTY))
        .setDisplayName(displayName)
        .setIconResId(iconResource)
        .build()


}
