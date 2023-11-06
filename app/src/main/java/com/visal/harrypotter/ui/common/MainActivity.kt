package com.visal.harrypotter.ui.common

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.visal.harrypotter.ui.common.theme.HarryPotterTheme
import com.visal.harrypotter.ui.navigation.AppNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            HarryPotterTheme(darkTheme = darkTheme) {
                navController = rememberNavController()
                AppNavigationGraph(
                    navController = navController,
                    darkTheme = darkTheme,
                    onThemeUpdated = { darkTheme = !darkTheme },
                )
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
}
