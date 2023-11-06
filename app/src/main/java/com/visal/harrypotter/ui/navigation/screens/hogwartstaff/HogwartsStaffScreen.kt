import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.ui.common.CharacterItemComponent
import com.visal.harrypotter.ui.common.Loader
import com.visal.harrypotter.ui.common.TopAppBarWithBack
import com.visal.harrypotter.ui.common.TopSearchBar
import com.visal.harrypotter.ui.common.util.AppConstant
import com.visal.harrypotter.ui.navigation.AppScreens
import com.visal.harrypotter.ui.navigation.screens.hogwartstaff.HogwartsStaffViewModel
import com.visal.utilities.ResourceState


private const val TAG = "HogwartsStaffScreen"

@Composable
fun HogwartsStaffScreen(
    viewModel: HogwartsStaffViewModel = hiltViewModel(),
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val hpCharacters by viewModel.hpCharacters.collectAsState()
    Surface {
        Column(modifier = Modifier.fillMaxHeight()) {
//        AppBarTextComponent(stringResource(id = R.string.all_characters))
            TopAppBarWithBack(
                title = stringResource(id = R.string.hogwarts_staffs),
                onBackClick = {
                    navController.popBackStack()
                },
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                isBackVisible = true
            )
            TopSearchBar(
                searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onSearchTriggered = {})
            HogwartsStaffsListComponent(hpCharacters, searchQuery, navController)
        }
    }

}

@Composable
fun HogwartsStaffsListComponent(
    hpCharacters: ResourceState<List<HpCharacter>>,
    searchQuery: String,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp) //
    ) {
        when (hpCharacters) {
            is ResourceState.Loading -> {
                Log.d(TAG, "Loading")
                item { Loader() }
            }

            is ResourceState.Success -> {
                val characterList = (hpCharacters as ResourceState.Success).data
                val filteredCharacters = characterList.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                            it.actor?.isNotBlank() == true && it.actor.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                }
                items(filteredCharacters) { character ->
                    CharacterItemComponent(character, onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = AppConstant.HP_CHAR_KEY,
                            value = character
                        )
                        navController.navigate(AppScreens.charDetailScreen.route)
                    })
                }

            }

            is ResourceState.Error -> {
                val error = (hpCharacters as ResourceState.Error)
                Log.d(TAG, "Error $error")
            }
        }
    }
}


@Preview
@Composable
private fun HogwartsStaffScreenPreview() {
//    HogwartsStaffScreen(navController = rememberNavController())
}