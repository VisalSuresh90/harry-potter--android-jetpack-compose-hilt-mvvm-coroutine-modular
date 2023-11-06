package com.visal.harrypotter.ui.navigation.screens.hogwartstaff

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.data.repository.HogwartsStaffRepository
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
class HogwartsStaffViewModel @Inject constructor(private val repository: HogwartsStaffRepository) :
    ViewModel() {

    companion object {
        const val TAG = "HogwartsStaffViewModel"
    }

    // Define a private mutable StateFlow to hold a list of Harry Potter characters
    private val _hpCharacters: MutableStateFlow<ResourceState<List<HpCharacter>>> =
        MutableStateFlow(ResourceState.Loading());

    // Define a public StateFlow to expose Harry Potter characters data
    val hpCharacters: StateFlow<ResourceState<List<HpCharacter>>> = _hpCharacters

    // Define a private mutable StateFlow to hold the search query
    private val _searchQuery = MutableStateFlow("")

    // Define a StateFlow to hold the search query string
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        getHogwartsStaff()
    }


    /**
     * Function for fetch Hogwarts Staff data from server
     */
    private fun getHogwartsStaff() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHogwartsStaff()
                .collectLatest { newsResponse ->
                    _hpCharacters.value = newsResponse
                }
        }
    }

    /**
     * Called when the search query is changed.
     *
     * @param newQuery The new search query entered by the user.
     */
    fun onSearchQueryChanged(newQuery: String) {
        Log.d(HogwartsStudentsViewModel.TAG, "onSearchQueryChanged $newQuery")
        // Update the value of _searchQuery LiveData with the new query
        _searchQuery.value = newQuery
    }

}