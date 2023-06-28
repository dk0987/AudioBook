package com.example.audiobook.feature_explore.presentation.nonFunctionalSearch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.audiobook.R
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.feature_audioBook.presentation.components.RecentlyPlayedCard
import com.example.audiobook.feature_audioBook.presentation.components.RecommendationCard
import com.example.audiobook.feature_audioBook.presentation.components.SectionHeading

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun NonFunctionalExplore(
    navController: NavController
) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Search",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        stickyHeader {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)){
                TextField(
                    value = "",
                    onValueChange = {} ,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.onBackground ,
                        textColor = MaterialTheme.colorScheme.onPrimary ,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    },
                    enabled = false,
                    shape = TextFieldDefaults.filledShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .clickable {
                                   navController.navigate(Routes.FunctionalExplore.screen)
                        },
                    label = {
                        Text(
                            text = "The Multiverse",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.background,
                            textAlign = TextAlign.Start
                        )
                    }
                )
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
                    SectionHeading(heading = "Recommendation")
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }

        item {
            for (i in 1..2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RecommendationCard(
                        bookName = "Catty ",
                        author = "Bhad M",
                        bookCover = painterResource(id = R.drawable.ninja)
                    )
                    RecommendationCard(
                        bookName = "Catty ",
                        author = "Bhad M",
                        bookCover = painterResource(id = R.drawable.dabi)
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
        item {
            Spacer(modifier = Modifier.height(110.dp))
        }
    }
}