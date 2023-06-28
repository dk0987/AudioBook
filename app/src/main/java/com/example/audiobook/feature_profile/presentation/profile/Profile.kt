package com.example.audiobook.feature_profile.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.audiobook.R
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.feature_audioBook.presentation.components.RecommendationCard
import com.example.audiobook.feature_audioBook.presentation.components.SectionHeading
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Profile(
    navController: NavController
) {
    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()
    val offsetY = collapsingToolbarScaffoldState.offsetY
    val progress = collapsingToolbarScaffoldState.toolbarState.progress

    val scrollState = rememberScrollState()

    var switch by remember{
        mutableStateOf(false)
    }
    
    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = collapsingToolbarScaffoldState,
        enabled = true,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed ,
        toolbar = {
            Spacer(modifier = Modifier.height(45.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    IconButton(onClick = {
                        navController.popBackStack(Routes.Home.screen , true)
                    },
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    if (progress < 0.3f){
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "IAmDK",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            color = MaterialTheme.colorScheme.onPrimary,

                            )

                    }
                }

                Row {
                    IconButton(onClick = {
                        navController.popBackStack(Routes.Home.screen , true)
                    },
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    IconButton(onClick = {
                        navController.popBackStack(Routes.Home.screen , true)
                    },
                        modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }


                }



            }
            Image(
                painter = painterResource(id = R.drawable.ninja),
                contentDescription = "" ,
                contentScale = ContentScale.Crop ,
                alpha = 0.7f,
                modifier = Modifier
                    .height(400.dp)
                    .parallax(0.4f)
                    .graphicsLayer {
                        alpha = progress
                    }
            )



            if (progress > 0.3f){

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .road(Alignment.BottomCenter, Alignment.BottomCenter)
                    .graphicsLayer {
                        alpha = progress
                    }
                )

                Text(
                    text = "IAmDK",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, bottom = 15.dp)
                        .road(Alignment.BottomStart, Alignment.BottomStart)
                        .graphicsLayer {
                            alpha = progress
                        }

                )

                Text(
                    text = "ydk08340@gmail.com",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraLight,
                    letterSpacing = 3.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                        .road(Alignment.BottomStart, Alignment.BottomStart)
                        .graphicsLayer {
                            alpha = progress
                        }

                )
            }

        }
    ) {
        LazyColumn(
            modifier = Modifier,
        ) {
            item{
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Notification",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 2.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Start
                        )

                        Switch(checked = switch, onCheckedChange = {
                                                 switch = it
                        } , colors = SwitchDefaults.colors(
                            uncheckedThumbColor = Color(0xff292929)
                        ), modifier = Modifier.size(10.dp))
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "View all Recently Played",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "View all Favorites",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }

            }

            item {
                Spacer(modifier = Modifier.height(110.dp))
            }
        }
    }

}