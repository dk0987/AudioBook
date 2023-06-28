package com.example.audiobook.feature_audioBook.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.audiobook.R

@ExperimentalFoundationApi
@Composable
fun BooksDetails(
    cover : String ,
    bookName : String,
    author : String ,
    description : String,
    onPlay : () -> Unit = {},
    onPause: () -> Unit = {},
    isPlaying : Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 20.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xff292929))
                .align(Alignment.BottomCenter)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.18f))
            Text(
                text = bookName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "- By $author",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = if (!isPlaying) onPlay else onPause,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = if (!isPlaying) Icons.Default.PlayArrow else Icons.Default.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color(0xff292929)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xff292929),
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = CardDefaults.elevatedShape,
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp
                            ),
                            modifier = Modifier.padding(
                                start = 5.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            ),

                            ) {
                            Text(
                                text = "Description",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.padding(
                                    horizontal = 10.dp,
                                    vertical = 5.dp
                                )
                            )
                        }
                    }

                }

                item {
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 5.dp
                        )
                    )
                    Spacer(modifier = Modifier.height(110.dp))
                }
            }

        }
        Image(
            painter = rememberImagePainter(
                data = cover,
                builder = {
                    crossfade(true)
                    size(OriginalSize)
                }),
            contentDescription = "",
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(15.dp))
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

    }
}