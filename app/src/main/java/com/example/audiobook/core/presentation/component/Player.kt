package com.example.audiobook.core.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.audiobook.R
import com.example.audiobook.core.domain.modal.Chapter

@Composable
fun Player(
    bookCover : String ,
    chapterList : List<Chapter>,
    onPlay : () -> Unit = {},
    onPause : () -> Unit = {},
    running : Float ,
    currentlyPlayingChapter : Chapter ,
    onNext : () -> Unit = {},
    onPrev : () -> Unit = {} ,
    isplaying : Boolean
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically ,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Image(
                painter = rememberImagePainter(data = bookCover, builder = {
                    crossfade(true)
                    size(OriginalSize)
                }),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp)
                    .shadow(10.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(40.dp))

        }

        item {
            Column (
                horizontalAlignment = Alignment.Start ,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = currentlyPlayingChapter.chapterTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = currentlyPlayingChapter.bookAuthor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Slider(value = running, onValueChange = {} , colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.onPrimary,
                activeTrackColor = MaterialTheme.colorScheme.onPrimary,
                inactiveTrackColor = Color(0xff292929)
            ))
            Spacer(modifier = Modifier.height(20.dp))


        }

        item {
            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .background(Color.White),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.fillMaxSize(0.5f)
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    IconButton(
                        onClick = {
                            if (isplaying) onPause() else onPlay()
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .background(Color.White),
                    ) {
                        Icon(
                            imageVector =  if (isplaying) Icons.Default.Close else Icons.Default.PlayArrow ,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.fillMaxSize(0.6f)

                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))


                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                            .background(Color.White),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.fillMaxSize(0.5f)

                        )
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.2f))
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Red
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

        }

        item {
            Spacer(modifier = Modifier.height(20.dp))

        }

        items(chapterList){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xff292929))
                    .padding(
                        horizontal = 10.dp,
                        vertical = 2.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    horizontalAlignment = Alignment.Start ,
                ){
                    Text(
                        text = it.chapterTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Chapter ${chapterList.indexOf(it)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Start
                    )
                }
                if (currentlyPlayingChapter != it){
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.White),
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background,
                            modifier = Modifier.fillMaxSize(0.8f)
                        )
                    }
                }


            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}