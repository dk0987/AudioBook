package com.example.audiobook.core.exoplayer

import android.content.ComponentName
import android.content.Context
import kotlinx.coroutines.guava.await
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.media3.common.Player
import androidx.media3.common.Player.*
import androidx.media3.session.MediaBrowser
import androidx.media3.session.SessionToken
import com.example.audiobook.core.di.AudioBookDispatcher
import com.example.audiobook.core.di.Dispatcher
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.core.exoplayer.mapper.asChapter
import com.example.audiobook.core.exoplayer.mapper.asMediaItem
import com.example.audiobook.core.exoplayer.states.ChapterAudioState
import com.example.audiobook.core.exoplayer.util.asPlaybackState
import com.example.audiobook.core.exoplayer.util.orDefaultTimestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.O)
@Singleton
class MusicServiceConnection @Inject constructor(
    @ApplicationContext context : Context ,
    @Dispatcher(AudioBookDispatcher.MAIN) mainDispatcher: CoroutineDispatcher
) {

    private var mediaBrowser : MediaBrowser? = null
    private val couroutineScope = CoroutineScope(mainDispatcher + SupervisorJob())

    private val _chapterAudioState = MutableStateFlow(ChapterAudioState())
    val chapterAudioState = _chapterAudioState.asStateFlow()

    val currentPosition = flow {
        while (currentCoroutineContext().isActive) {
            val currentPosition = mediaBrowser?.currentPosition ?: 0
            emit(currentPosition)
            delay(1000L)
        }
    }

    init {
        couroutineScope.launch {
            mediaBrowser = MediaBrowser.Builder(
                context,
                SessionToken(context, ComponentName(context, MusicService::class.java))
            ).buildAsync().await().apply { addListener(PlayerListener()) }
        }
    }

    fun skipPrevious() = mediaBrowser?.run {
        seekToPrevious()
        play()
    }

    fun play() = mediaBrowser?.play()
    fun pause() = mediaBrowser?.pause()

    fun skipNext() = mediaBrowser?.run {
        seekToNext()
        play()
    }

    fun skipTo(position: Long) = mediaBrowser?.run {
        seekTo(position)
        play()
    }

    fun playSongs(
        chapters: List<Chapter>,
        startIndex: Int = 0,
        startPositionMs: Long = 0
    ) {

        mediaBrowser?.run {
            clearMediaItems()
            setMediaItems(chapters.map { it.asMediaItem() }, startIndex, startPositionMs)
            _chapterAudioState.update {
                it.copy(
                    chapters = chapters
                )
            }
            prepare()
        }

    }


    private inner class PlayerListener : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            if (events.containsAny(
                    EVENT_PLAYBACK_STATE_CHANGED,
                    EVENT_MEDIA_METADATA_CHANGED,
                    EVENT_PLAY_WHEN_READY_CHANGED
                )
            ) {
                updateMusicState(player)
            }

        }
    }

    private fun updateMusicState(player: Player) = with(player) {
        _chapterAudioState.update {
            it.copy(
                chapter = currentMediaItem?.asChapter(),
                playbackState = playbackState.asPlaybackState(),
                playWhenReady = playWhenReady,
                duration = duration.orDefaultTimestamp()
            )
        }
    }


}