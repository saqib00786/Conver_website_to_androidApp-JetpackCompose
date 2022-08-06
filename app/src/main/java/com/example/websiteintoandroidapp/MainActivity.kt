package com.example.websiteintoandroidapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.websiteintoandroidapp.components.TopAppBar
import com.example.websiteintoandroidapp.ui.theme.WebsiteIntoAndroidAppTheme
import com.example.websiteintoandroidapp.util.BASE_URL
import com.google.accompanist.web.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebsiteIntoAndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    WebBrowser()
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Preview
@Composable
fun WebBrowser() {
//    val url by remember {
//        mutableStateOf(BASE_URL)
//    }
//
    val Url = remember {
        mutableStateOf(BASE_URL)
    }


    val state = rememberWebViewState(url = Url.value)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    Column(modifier = Modifier.fillMaxSize()) {
//        TopAppBar(navigator, textFieldValue,Url)

        /* BasicTextField(
            value = textFieldValue,
            modifier = Modifier.weight(9f),
            onValueChange = { textFieldValue = it },
            maxLines = 1
        )

        if(state.errorsForCurrentRequest.isNotEmpty()){
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning",
                tint = MaterialTheme.colorScheme.errorContainer
            )
        }
    }*/
        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }
            }
        }


        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val lodingState = state.loadingState
        val animationSpec by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.progress_bar))
        if (lodingState is LoadingState.Loading) {
            LottieAnimation(composition =animationSpec,
            iterations = Int.MAX_VALUE
                )
        }
    }

}