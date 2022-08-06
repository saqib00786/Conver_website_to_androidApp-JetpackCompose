package com.example.websiteintoandroidapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.web.WebViewNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navigator: WebViewNavigator, textFieldValue: String, URL: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.error)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
        ) {
            IconButton(onClick = { navigator.navigateBack()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.background
                )
            }
            IconButton(onClick = { navigator.navigateForward()}) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Youtube", fontSize = 24.sp, color = Color.White)
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            IconButton(onClick = {navigator.reload() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colorScheme.background
                )
            }
            IconButton(onClick = { URL.value = textFieldValue}) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}