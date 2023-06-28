package com.example.audiobook.feature_authentication.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.audiobook.R
import com.example.audiobook.core.presentation.util.Routes
import com.example.audiobook.feature_authentication.presentation.component.SignInWithGoogle
import kotlinx.coroutines.delay

@Composable
fun Login(
    navController: NavController
) {
    var logoVisibility by remember{
        mutableStateOf(false)
    }

    var buttonVisibility by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        delay(100)
        logoVisibility = true
        delay(2500)
        buttonVisibility = true
    }

    Box(
        modifier = Modifier.fillMaxSize() ,
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            AnimatedVisibility(
                visible = logoVisibility ,
                enter = fadeIn(tween(2000))
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxHeight(0.40f)
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.05f))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White , fontSize = 14.sp , fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic)) {
                                append("Deep Dive into Ocean of Knowledge with  ")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White , fontSize = 40.sp , letterSpacing = 2.sp )) {
                                append("Audio Book")
                            }
                        },
                        modifier = Modifier.padding(horizontal = 20.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 50.sp
                    )
                }


            }
            
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            AnimatedVisibility(
                visible = buttonVisibility ,
                enter = expandVertically(spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow) , Alignment.CenterVertically)
            ) {
                SignInWithGoogle(
                    onClick = { navController.navigate(Routes.Home.screen)},
                    text = "Sign in with GOOGLE",
                    icon = painterResource(id = R.drawable.google)
                )
            }



        }
    }
}