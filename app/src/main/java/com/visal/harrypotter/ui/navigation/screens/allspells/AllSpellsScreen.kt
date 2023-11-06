package com.visal.harrypotter.ui.navigation.screens.allspells

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.Spell
import com.visal.harrypotter.ui.common.Loader
import com.visal.harrypotter.ui.common.TopAppBarWithBack
import com.visal.harrypotter.ui.common.TopSearchBar
import com.visal.utilities.ResourceState


private const val TAG = "AllSpellsScreen"

@Composable
fun AllSpellsScreen(
    viewModel: AllSpellsViewModel = hiltViewModel(), navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val spells by viewModel.allSpells.collectAsState()
    Surface {
        Column(modifier = Modifier.fillMaxHeight()) {
            TopAppBarWithBack(
                title = stringResource(id = R.string.all_spells),
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
            SpellsListComponent(spells, searchQuery)
        }
    }
}

@Composable
fun SpellsListComponent(spells: ResourceState<List<Spell>>, searchQuery: String) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp) //
    ) {
        when (spells) {
            is ResourceState.Loading -> {
                Log.d(TAG, "Loading")
                item { Loader() }
            }

            is ResourceState.Success -> {
                val characterList = (spells as ResourceState.Success).data
                val filteredCharacters = characterList.filter {
                    it.name.contains(searchQuery, ignoreCase = true) ||
                            it.description?.isNotBlank() == true && it.description.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                }
                items(filteredCharacters) { character -> SpellItemComponent(character) }

            }

            is ResourceState.Error -> {
                val error = (spells as ResourceState.Error)
                Log.d(TAG, "Error $error")
            }
        }
    }
}