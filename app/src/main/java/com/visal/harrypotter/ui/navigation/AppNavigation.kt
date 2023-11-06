package com.visal.harrypotter.ui.navigation

import HogwartsStaffScreen
import HogwartsStudentsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.ui.common.util.AppConstant
import com.visal.harrypotter.ui.navigation.screens.allcharacters.AllCharactersScreen
import com.visal.harrypotter.ui.navigation.screens.allspells.AllSpellsScreen
import com.visal.harrypotter.ui.navigation.screens.characterdetails.CharacterDetailScreen
import com.visal.harrypotter.ui.navigation.screens.home.HomeScreen
import com.visal.harrypotter.ui.navigation.screens.splash.SplashScreen


/**
 * Defines the application's navigation graph using Jetpack Navigation Compose.
 * It specifies the navigation controller, theme preferences, and theme update callback.
 * The navigation controller helps manage navigation within the app.
 * @param navController The NavHostController responsible for managing navigation within the app.
 * @param darkTheme A boolean indicating whether the app is in dark theme mode.
 * @param onThemeUpdated A lambda function to handle theme updates in the app.
 */
@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {
        composable(Routes.SPLASH_SCREEN) {
            // Define a composable route for the HOME_SCREEN
            SplashScreen(
               durationMillis = 2000,
                navigateToMain = {
                    navController.navigate(route = AppScreens.homeScreen.route)
                }
            )
        }
        composable(Routes.HOME_SCREEN) {
            // Define a composable route for the HOME_SCREEN
            HomeScreen(
                navController = navController, // Pass the navigation controller
                darkTheme = darkTheme, // Pass the dark theme configuration
                onThemeUpdated = onThemeUpdated // Pass the callback for theme updates
            )
        }
        composable(Routes.ALL_CHARACTERS_SCREEN) {
            AllCharactersScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(Routes.HOGWARTS_STAFF_SCREEN) {
            HogwartsStaffScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(Routes.HOGWARTS_STUDENTS_SCREEN) {
            HogwartsStudentsScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(Routes.ALL_SPELLS_SCREEN) {
            AllSpellsScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(Routes.CHARACTER_DETAIL_SCREEN) {
            // Pass the selected HpCharacter object argument to CharacterDetailScreen
            val hpCharacter =
                navController.previousBackStackEntry?.savedStateHandle?.get<HpCharacter>(AppConstant.HP_CHAR_KEY)
            // Check if hpCharacter is not null
            hpCharacter?.let { it1 ->
                // If hpCharacter is not null, navigate to the CharacterDetailScreen composable
                CharacterDetailScreen(
                    navController = navController, // Pass the navigation controller
                    hpCharacter = it1, // Pass the retrieved Harry Potter character object
                    darkTheme = darkTheme, // Pass the dark theme configuration
                    onThemeUpdated = onThemeUpdated // Pass the callback for theme updates
                )
            }

        }
    }
}
