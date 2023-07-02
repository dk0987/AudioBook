package com.example.audiobook.feature_authentication.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.core.presentation.util.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    value : String,
    leadingIcon : ImageVector,
    password : Boolean = false,
    placeholder : String,
    keyboardType : KeyboardType = KeyboardType.Text,
    onValueChange : (String) -> Unit
) {

    var passwordVisible by remember{
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = {
                 onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onBackground ,
            textColor = MaterialTheme.colorScheme.onPrimary ,
        ),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.background
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisible = !passwordVisible
            }) {
                if (password){
                    if (passwordVisible){
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                    else{
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }

        },
        shape = TextFieldDefaults.filledShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .clickable {

            },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.background,
                textAlign = TextAlign.Start
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (password && passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}