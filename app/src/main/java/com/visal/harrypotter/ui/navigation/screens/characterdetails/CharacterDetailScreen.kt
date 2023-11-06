package com.visal.harrypotter.ui.navigation.screens.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.ui.common.TopAppBarWithBack
import com.visal.harrypotter.ui.common.util.AppConstant
import com.visal.harrypotter.ui.common.util.StringUtil


const val TAG = "CharacterDetailScreen"

@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    hpCharacter: HpCharacter,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    Surface {
        Column(modifier = Modifier.fillMaxHeight()) {
            TopAppBarWithBack(
                title = hpCharacter.name,
                onBackClick = {
                    navController.popBackStack()
                },
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                isBackVisible = true
            )
            val imageUrl = hpCharacter.image?.takeIf { it.isNotEmpty() }
                ?: AppConstant.NO_IMAGE_AVAILABLE_URL
            CharImage(imageUrl)
            Spacer(modifier = Modifier.height(8.dp))
            LabelTextRowDetails(hpCharacter)
        }
    }

}


/**
 * Composable function for displaying detailed information about an HPCharacter using a series of LabelTextRow composites.
 *
 * @param hpCharacter The HPCharacter object containing the character's details.
 */
@Composable
fun LabelTextRowDetails(
    hpCharacter: HpCharacter
) {
    // Remember the scroll state for vertical scrolling within the column
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // Repeat LabelTextRow composites for various character details
        repeat(1) {
            LabelTextRow("Alternate Names", StringUtil.joinListWithCommas(hpCharacter.alternateNames ?: emptyList()))
            LabelTextRow("actor", hpCharacter.actor)
            LabelTextRow("species", hpCharacter.species)
            LabelTextRow("gender", hpCharacter.gender)
            LabelTextRow("house", hpCharacter.house)
            LabelTextRow("date Of Birth", hpCharacter.dateOfBirth)
            LabelTextRow("wizard", if (hpCharacter.wizard == true) "Yes" else "No")
            LabelTextRow("ancestry", hpCharacter.ancestry)
            LabelTextRow("eyeColour", hpCharacter.eyeColour)
            LabelTextRow("hairColour", hpCharacter.hairColour)
            LabelTextRow("patronus", hpCharacter.patronus)
            LabelTextRow("hogwartsStudent", if (hpCharacter.hogwartsStudent == true) "Yes" else "No")
            LabelTextRow("hogwartsStaff", if (hpCharacter.hogwartsStaff == true) "Yes" else "No")
            LabelTextRow("alive", if (hpCharacter.alive == true) "Yes" else "No")
        }
    }
}