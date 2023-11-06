package com.visal.harrypotter.ui.navigation.screens.housecharacters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.House
import com.visal.harrypotter.ui.common.TopAppBarWithBack
import com.visal.harrypotter.ui.common.util.AppConstant
import com.visal.harrypotter.ui.navigation.AppScreens

@Composable
fun HouseScreen(
    viewModel: HouseCharactersViewModel = hiltViewModel(),
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    Surface {
        val houseList by viewModel.houseList.collectAsState()
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBarWithBack(
                title = stringResource(id = R.string.houses),
                onBackClick = {
                    navController.popBackStack()
                },
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                isBackVisible = true
            )
            HouseList(houseList,navController)
        }
    }
}


@Composable
fun HouseList(houseList: List<House>,navController: NavHostController,) {
    LazyColumn {
        items(houseList) { house ->
//            Spacer(modifier = Modifier.height(16.dp))
            HouseItem(house = house,onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = AppConstant.HOUSE_ID,
                    value = house
                )
                navController.navigate(AppScreens.houseCharactersScreen.route)
            })
        }
    }
}

