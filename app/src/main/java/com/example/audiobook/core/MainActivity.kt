package com.example.audiobook.core

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.core.domain.util.convertToProgress
import com.example.audiobook.core.presentation.component.MiniPLayer
import com.example.audiobook.core.presentation.component.Player
import com.example.audiobook.core.presentation.component.StandardScaffold
import com.example.audiobook.core.presentation.ui.theme.AudioBookTheme
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.feature_audioBook.presentation.home.Home
import com.example.audiobook.feature_authentication.presentation.register.Register
import com.example.audiobook.feature_authentication.presentation.splash.Splash
import com.example.audiobook.feature_explore.presentation.functionalExplore.FunctionalExplore
import com.example.audiobook.feature_explore.presentation.nonFunctionalSearch.NonFunctionalExplore
import com.example.audiobook.feature_favourites.presentation.favourites.Favourites
import com.example.audiobook.feature_recentlyPlayed.presentation.recentlyPlayed.RecentlyPlayed
import com.example.audiobook.feature_profile.presentation.profile.Profile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val snackBarHostState = remember{ SnackbarHostState() }
                    val viewModel = hiltViewModel<MainViewModel>()
                    val chapterAudioState = viewModel.chapterAudioState.collectAsState()
                    val chapterList = remember {
                        mutableStateOf<List<Chapter>>(emptyList())
                    }
                    val currentPosition by viewModel.currentPosition.collectAsState()

                    val progress by animateFloatAsState(
                        targetValue = convertToProgress(
                            count = currentPosition,
                            total = chapterAudioState.value.duration
                        ),
                        label = ""
                    )

                    val infiniteTransition = rememberInfiniteTransition(label = "")
                    val animatedColor by infiniteTransition.animateColor(
                        initialValue = MaterialTheme.colorScheme.tertiary,
                        targetValue = Color(0xffff9933),
                        animationSpec = infiniteRepeatable(
                            animation = tween(5000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ), label = ""
                    )
                    val sheetState = rememberModalBottomSheetState(
                        initialValue = ModalBottomSheetValue.Hidden,
                        skipHalfExpanded = true,
                        confirmValueChange = { it != ModalBottomSheetValue.Expanded }
                    )
                    val coroutineScope = rememberCoroutineScope()

                    BackHandler(sheetState.isVisible) {
                        coroutineScope.launch { sheetState.hide() }
                    }


                    
                    StandardScaffold(
                        navController = navController,
                        isVisible = navBackStackEntry?.destination?.route in listOf(
                            Routes.Home.screen,
                            Routes.NonFunctionalExplore.screen
                        ) && !sheetState.isVisible,
                        content = {padding ->
                            ModalBottomSheetLayout(
                                sheetContent = {
                                               Box(modifier = Modifier
                                                   .fillMaxSize()
                                                   .background(
                                                       brush = Brush.verticalGradient(
                                                           listOf(
                                                               animatedColor,
                                                               MaterialTheme.colorScheme.background
                                                           ),
                                                           startY = 50f
                                                       )
                                                   )
                                                   .padding(horizontal = 20.dp)

                                               ){
                                                   chapterAudioState.value.chapter?.let {
                                                       Player(
                                                           bookCover = it.bookCoverUri.toString(),
                                                           currentlyPlayingChapter = it ,
                                                           chapterList = chapterAudioState.value.chapters ,
                                                           onPlay = {
                                                               viewModel.play()
                                                           },
                                                           onNext = {
                                                                    viewModel.next()
                                                           },
                                                           onPrev = {
                                                                    viewModel.prev()
                                                           },
                                                           onPause = {
                                                                     viewModel.pause()
                                                           },
                                                           running = progress ,
                                                           isplaying = chapterAudioState.value.playWhenReady
                                                       )
                                                   }

                                               }
                                } ,
                                modifier = Modifier.fillMaxSize(),
                                sheetState = sheetState
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 180.dp)){
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.4f)
                                            .align(Alignment.TopCenter)
                                            .background(
                                                brush = Brush
                                                    .verticalGradient(
                                                        colors = listOf(
                                                            Color.Transparent,
                                                            Color.Transparent
                                                        ),
                                                    )
                                            )
                                    )
                                }
                                NavHost(
                                    navController = navController,
                                    startDestination = Routes.Register.screen
                                ){
                                    composable(
                                        route = Routes.Splash.screen
                                    ){
                                        Splash(navController)
                                    }

                                    composable(
                                        route = Routes.Register.screen
                                    ){
                                        Register()
                                    }

                                    composable(
                                        route = Routes.Home.screen
                                    ){
                                        Home(
                                            navController,
                                            padding,
                                        )
                                    }
                                    composable(
                                        route = Routes.NonFunctionalExplore.screen
                                    ){
                                        NonFunctionalExplore(
                                            navController
                                        )
                                    }
                                    composable(
                                        route = Routes.FunctionalExplore.screen
                                    ){
                                        FunctionalExplore(
                                            navController
                                        )
                                    }
                                    composable(
                                        route = Routes.Profile.screen
                                    ){
                                        Profile(
                                            navController
                                        )
                                    }
                                    composable(
                                        route = Routes.Favourites.screen
                                    ){
                                        Favourites()
                                    }
                                    composable(
                                        route = Routes.RecentlyPlayed.screen
                                    ){
                                        RecentlyPlayed()
                                    }

                                }

                                AnimatedVisibility(
                                    visible =
                                        navBackStackEntry?.destination?.route !in listOf(Routes.Login.screen , Routes.Profile.screen) &&
                                        !sheetState.isVisible &&
                                        chapterAudioState.value.chapter != null,
                                    enter = slideInHorizontally(),
                                    exit = slideOutHorizontally()
                                ) {

                                    Box(modifier = Modifier
                                        .fillMaxSize()
                                        .padding(bottom = padding.calculateBottomPadding())
                                    ){
                                        Box(modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                animatedColor
                                            )
                                            .align(Alignment.BottomCenter)
                                            .clickable {
                                                coroutineScope.launch {
                                                    sheetState.show()
                                                }
                                            }
                                        ){
                                            chapterAudioState.value.chapter?.let {
                                                MiniPLayer(
                                                    bookCover = it.bookCoverUri.toString(),
                                                    chapterTitle = it.chapterTitle  ,
                                                    author = it.bookAuthor ,
                                                    isPlaying = chapterAudioState.value.playWhenReady
                                                )
                                            }

                                        }
                                    }
                                }

                            }

                        },
                        snackBarHostState = snackBarHostState,
                        animatedColor = animatedColor
                    )



                }
            }
        }
    }
}

