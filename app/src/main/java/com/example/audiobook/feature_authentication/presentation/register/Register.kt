package com.example.audiobook.feature_authentication.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.R
import com.example.audiobook.feature_authentication.presentation.component.StandardButton
import com.example.audiobook.feature_authentication.presentation.component.StandardTextField

@Composable
fun Register() {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
    ){
        item {
            Text(
                text = "Register".uppercase(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))

        }

        item {

            StandardTextField(
                value = "",
                leadingIcon = Icons.Filled.AccountBox,
                placeholder = "Enter username" ,
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(8.dp))


            StandardTextField(
                value = "",
                leadingIcon = Icons.Filled.Email,
                placeholder = "Enter email" ,
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(8.dp))

            StandardTextField(
                value = "",
                leadingIcon = Icons.Filled.Password,
                placeholder = "Enter password" ,
                onValueChange = {},
                password = true ,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(8.dp))

            StandardTextField(
                value = "",
                leadingIcon = Icons.Filled.Password,
                placeholder = "Re-enter password" ,
                onValueChange = {},
                password = true ,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(8.dp))


            StandardButton(
                onClick = { /*TODO*/ },
                text = "Register",
                icon = painterResource(id = R.drawable.register)
            )

        }

    }

    Box (
        modifier = Modifier.fillMaxSize().padding(bottom = 5.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already Have An Account ?",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp,
                color = Color.White,
            )
            Text(
                text = "Login In",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = Color(0xff4286f5),
            )
        }

    }
    
}