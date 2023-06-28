package com.example.audiobook.core

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobook.core.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel  @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection
) : ViewModel() {

    val chapterAudioState = musicServiceConnection.chapterAudioState
    val currentPosition = musicServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily ,
        initialValue = 0
    )

    fun play(){
        viewModelScope.launch {
            musicServiceConnection.play()
        }
    }

    fun pause(){
        viewModelScope.launch {
            musicServiceConnection.pause()
        }
    }

     fun next(){
        viewModelScope.launch {
            musicServiceConnection.skipNext()
        }
    }

     fun prev(){
        viewModelScope.launch {
            musicServiceConnection.skipPrevious()
        }
    }



}