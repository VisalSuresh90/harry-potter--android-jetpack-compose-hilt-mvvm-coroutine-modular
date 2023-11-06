package com.visal.harrypotter.ui.navigation.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.visal.harrypotter.ui.common.util.AppConstant
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(durationMillis: Int, navigateToMain: () -> Unit) {
    // Use a flag to keep track of whether the timer has completed
    var timerFinished by remember { mutableStateOf(false) }

    // Start a coroutine to delay for the specified duration
    LaunchedEffect(key1 = timerFinished) {
        delay(durationMillis.toLong())
        timerFinished = true
    }

    // Display the splash screen content
    if (timerFinished) {
        navigateToMain()
    } else {
        ImageComposable()
    }
}


/**
 * A composable function for displaying an image using AsyncImage.
 */
@Composable
fun ImageComposable() {
    // Load your image (replace with your image resource or URL)
    Surface(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = AppConstant.SPLASH_IMAGE_URL,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
