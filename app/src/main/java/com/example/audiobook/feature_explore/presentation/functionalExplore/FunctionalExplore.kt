package com.example.audiobook.feature_explore.presentation.functionalExplore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.navigation.NavController
import com.example.audiobook.R
import com.example.audiobook.core.presentation.component.BookItem
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.feature_audioBook.presentation.components.RecommendationCard
import com.example.audiobook.feature_audioBook.presentation.components.SectionHeading

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun FunctionalExplore(
    navController: NavController
){
    LazyColumn(modifier = Modifier.fillMaxSize()){

        stickyHeader {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
            ){
                TextField(
                    value = "",
                    onValueChange = {} ,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.onBackground ,
                        textColor = MaterialTheme.colorScheme.onPrimary ,
                    ),
                    trailingIcon = {
                        IconButton(onClick = { navController.popBackStack(Routes.NonFunctionalExplore.screen , false) }) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }

                    },
                    shape = TextFieldDefaults.filledShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 15.dp)
                        .clickable {
                            navController.navigate(Routes.FunctionalExplore.screen)
                        },
                    placeholder = {
                        Text(
                            text = "What do you want ot listen ?",
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

        items(10){
            BookItem()
        }
        
        item { 
            Spacer(modifier = Modifier.height(60.dp))
        }



    }
}