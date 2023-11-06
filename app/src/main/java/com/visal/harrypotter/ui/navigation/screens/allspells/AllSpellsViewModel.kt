package com.visal.harrypotter.ui.navigation.screens.allspells

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visal.harrypotter.data.model.Spell
import com.visal.harrypotter.data.repository.AllSpellsRepository
import com.visal.harrypotter.ui.navigation.screens.hogwartstudents.HogwartsStudentsViewModel
import com.visal.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSpellsViewModel @Inject constructor(private val repository: AllSpellsRepository) :
    ViewModel() {

    companion object {
        const val TAG = "AllSpellsViewModel"
    }


    // Define a private mutable StateFlow to hold a list of Spells
    private val _allSpells: MutableStateFlow<ResourceState<List<Spell>>> =
        MutableStateFlow(ResourceState.Loading());

    // Define a public StateFlow to expose Spell data
    val allSpells: StateFlow<ResourceState<List<Spell>>> = _allSpells

    // Define a private mutable StateFlow to hold the search query
    private val _searchQuery = MutableStateFlow("")

    // Define a StateFlow to hold the search query string
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        Log.d(TAG, "init called")
        getAllSpells()
    }

    /**
     * Function for fetch All Spell data from server
     */
    private fun getAllSpells() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSpells()
                .collectLatest {
                    _allSpells.value = it
                }
        }
    }

    /**
     * Called when the search query is changed.
     *
     * @param newQuery The new search query entered by the user.
     */
    fun onSearchQueryChanged(newQuery: String) {
        Log.d(HogwartsStudentsViewModel.TAG,"onSearchQueryChanged $newQuery")
        // Update the value of _searchQuery LiveData with the new query
        _searchQuery.value = newQuery
    }

}