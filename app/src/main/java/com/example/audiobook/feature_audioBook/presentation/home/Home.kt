package com.example.audiobook.feature_audioBook.presentation.home

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.audiobook.R
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.core.exoplayer.states.PlaybackState
import com.example.audiobook.core.presentation.component.LoadingRecommendationCard
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.core.presentation.util.category
import com.example.audiobook.feature_audioBook.presentation.components.BooksDetails
import com.example.audiobook.feature_audioBook.presentation.components.HomeTab
import com.example.audiobook.feature_audioBook.presentation.components.RecentlyPlayedCard
import com.example.audiobook.feature_audioBook.presentation.components.RecommendationCard
import com.example.audiobook.feature_audioBook.presentation.components.SectionHeading
import com.example.audiobook.feature_audioBook.presentation.components.TrendingCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Home(
    navController: NavController ,
    contentPaddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val states = viewModel.homeState.value

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmValueChange = { it != ModalBottomSheetValue.Expanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            states.selectedBook?.let { book ->
                BooksDetails(
                    cover = book.cover,
                    bookName = book.bookName,
                    author = book.author,
                    description = book.description,
                    isPlaying = viewModel.chapterAudioState.value.playWhenReady
                            && viewModel.chapterAudioState.value.chapter?.bookId == book._id,
                    onPlay = {
                        viewModel.onEvent(HomeEvent.OnPlay(book._id))
                        if (states.chapters.isNotEmpty()){
                            viewModel.play()
                        }
                    },
                    onPause= {
                        viewModel.onEvent(HomeEvent.OnPlay(book._id))
                        if (states.chapters.isNotEmpty()){
                            viewModel.play()
                        }
                    }
                )

            }
        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize(),
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Transparent,
        sheetElevation = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()){

            LazyColumn {

                item {
                    HomeTab(onProfile = {
                        navController.navigate(Routes.Profile.screen)
                    })
                    Spacer(modifier = Modifier.height(5.dp))
                }

                item {
                    SectionHeading(heading = "Trending")
                    Spacer(modifier = Modifier.height(5.dp))
                }

                item {

                    if (states.categorisedBook.isEmpty() || states.categorisedBookLoading){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xff292929))
                        )
                    }

                    else{
                        HorizontalPager(pageCount = states.categorisedBook.size , ) {
                            TrendingCard(
                                bookName = "#${it+1} "+states.categorisedBook[it].bookName,
                                author = states.categorisedBook[it].author,
                                cover = states.categorisedBook[it].cover,
                            ){
                                viewModel.onEvent(HomeEvent.OnBookDetailEvent(states.categorisedBook[it]))
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            }
                        }
                    }

                }

                item {
                    SectionHeading(heading = "Recently Played")
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {

                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        item{
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                        items(2) {
                            RecentlyPlayedCard()
                            Spacer(modifier = Modifier.width(15.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            LazyRow{
                                items(category){
                                    SectionHeading(heading = it , selected = states.selectedCategory == category.indexOf(it) ){
                                        if (category.indexOf(it) != states.selectedCategory){
                                            viewModel.onEvent(HomeEvent.OnCategoryEvent(category.indexOf(it)))
                                        }
                                    }
                                }
                            }
                        }

                    }

                }

                item{
                    Spacer(modifier = Modifier.height(5.dp))

                    LazyRow{
                        if (states.categorisedBookLoading || states.categorisedBook.isEmpty()){

                            items(5){
                                LoadingRecommendationCard()
                            }

                        }

                        else{

                            items(states.categorisedBook){
                                RecommendationCard(
                                    bookName = it.bookName,
                                    author = it.author,
                                    bookCover = rememberImagePainter(data = it.cover , builder = {
                                        this.crossfade(true)
                                        this.size(OriginalSize)
                                    })
                                ){
                                    viewModel.onEvent(HomeEvent.OnBookDetailEvent(it))
                                    coroutineScope.launch {
                                        sheetState.show()
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(120.dp))

                }


            }

        }

    }
}
