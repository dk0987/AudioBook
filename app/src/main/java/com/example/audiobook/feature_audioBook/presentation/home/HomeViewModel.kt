package com.example.audiobook.feature_audioBook.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobook.core.data.util.Resource
import com.example.audiobook.core.exoplayer.MusicServiceConnection
import com.example.audiobook.feature_audioBook.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val musicServiceConnection : MusicServiceConnection
) : ViewModel() {

    private val _homeState = mutableStateOf(HomeStates())
    val homeState : State<HomeStates> = _homeState

    val chapterAudioState = musicServiceConnection.chapterAudioState


    init {
        getBookByCategory(homeState.value.selectedCategory)
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnCategoryEvent -> {
                _homeState.value = homeState.value.copy(
                    selectedCategory = event.value
                )
                getBookByCategory(homeState.value.selectedCategory)
            }
            is HomeEvent.OnBookDetailEvent -> {
                _homeState.value = homeState.value.copy(
                    selectedBook = event.book
                )
            }
            is HomeEvent.OnPlay ->{
                getChapterList(event.bookId)

            }
        }
    }

    private fun getBookByCategory(category : Int){
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                categorisedBookLoading = true
            )
            when(val result = bookRepository.getBookByCategory(category)){
                is Resource.Success -> {
                    println("Category ${homeState.value.selectedCategory}" + result.data)
                    _homeState.value = homeState.value.copy(
                        categorisedBook = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        error = result.message
                    )
                }

            }
            _homeState.value = homeState.value.copy(
                categorisedBookLoading = false
            )
        }
    }

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

    private fun getChapterList(bookId : String){
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                categorisedBookLoading = true
            )
            when(val result = bookRepository.getChapters(bookId)){
                is Resource.Success -> {
                    _homeState.value = homeState.value.copy(
                        chapters = result.data ?: emptyList()
                    )
                    musicServiceConnection.playSongs(
                        chapters = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        error = result.message
                    )
                }
            }
            _homeState.value = homeState.value.copy(
                categorisedBookLoading = false
            )
        }
    }
}