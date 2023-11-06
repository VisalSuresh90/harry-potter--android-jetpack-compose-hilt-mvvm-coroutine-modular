package com.visal.harrypotter.ui.navigation

/**
 * Sealed class representing the different screens in the application, each associated with a specific route.
 *
 * @property route The route associated with the screen.
 */

sealed class AppScreens(val route:String) {
    // Define object instances for each screen
    object homeScreen :AppScreens(route = Routes.HOME_SCREEN)
    object allCharacterScreen :AppScreens(route = Routes.ALL_CHARACTERS_SCREEN)
    object hogwartsStaffsScreen :AppScreens(route = Routes.HOGWARTS_STAFF_SCREEN)
    object hogwartsStudentsScreen :AppScreens(route = Routes.HOGWARTS_STUDENTS_SCREEN)
    object allSpellsScreen :AppScreens(route = Routes.ALL_SPELLS_SCREEN)
    object charDetailScreen :AppScreens(route = Routes.CHARACTER_DETAIL_SCREEN)
    object houseScreen :AppScreens(route = Routes.HOUSE_SCREEN)
    object houseCharactersScreen :AppScreens(route = Routes.HOUSE_CHARACTERS_SCREEN)
}