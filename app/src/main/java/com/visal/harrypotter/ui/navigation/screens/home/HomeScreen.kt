package com.visal.harrypotter.ui.navigation.screens.home

import ImageBox
import MenuBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.visal.harrypotter.R
import com.visal.harrypotter.ui.common.TopAppBarWithBack
import com.visal.harrypotter.ui.navigation.AppScreens

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        TopAppBarWithBack(
            title = stringResource(id = R.string.app_name),
            onBackClick = {
                navController.popBackStack()
            },
            darkTheme = darkTheme,
            onThemeUpdated = onThemeUpdated,
            isBackVisible = false
        )
        ImagesBox(viewModel)
        MenuListComponent(viewModel, navController)
    }

}


/**
 * Composable function that represent the menu list.
 */
@Composable
fun MenuListComponent(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        repeat(1) {
            MenuBox(viewModel.menuList[0], onClick = {
                navController.navigate(route = AppScreens.allCharacterScreen.route)
            })
            MenuBox(viewModel.menuList[1], onClick = {
                navController.navigate(route = AppScreens.hogwartsStudentsScreen.route)
            })
            MenuBox(viewModel.menuList[2], onClick = {
                navController.navigate(route = AppScreens.hogwartsStaffsScreen.route)
            })
            MenuBox(viewModel.menuList[3], onClick = {
                navController.navigate(route = AppScreens.allSpellsScreen.route)
            })

        }
    }

}

/**
 * Composable function that represent auto sliding image
 */
@Composable
private fun ImagesBox(
    viewModel: HomeViewModel
) {
    val currentImage by viewModel.currentImage.collectAsState()
    // Start updating the value when the screen is launched
    LaunchedEffect(Unit) {
        viewModel.startUpdatingValue(5000L)
    }
    ImageBox(url = currentImage)
}


@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}