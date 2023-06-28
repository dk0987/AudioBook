package com.example.audiobook.feature_authentication.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.R

@Composable
fun SignInWithGoogle(
    onClick : () -> Unit,
    text : String ,
    icon : Painter
) {
    Button(
        onClick = onClick  ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff4286f5),
        ),
        shape = ButtonDefaults.outlinedShape ,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp ,
            pressedElevation = 2.dp
        ),
        modifier = Modifier.animateContentSize()
    ) {
        Image(
            painter = icon,
            contentDescription = "Google" ,
            modifier = Modifier
                .size(30.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.fillMaxWidth(0.05f))
        Text(
            text = text,
            fontSize = 18.sp ,
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}