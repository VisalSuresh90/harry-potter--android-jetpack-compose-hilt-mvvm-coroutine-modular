package com.visal.harrypotter.ui.navigation.screens.hogwartstudents

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.data.repository.HogwartsStudentsRepository
import com.visal.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HogwartsStudentsViewModel @Inject constructor(private val repository: HogwartsStudentsRepository) :
    ViewModel() {

    companion object {
        const val TAG = "HogwartsStudentsViewModel"
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
        getHogwartsStudents()
    }

    /**
     * Function for fetch Hogwarts Students data from server
     */
    private fun getHogwartsStudents() {
        // Using a Kotlin extension function to launch a coroutine in the viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            // Inside the coroutine, we're making a network request to the repository to get Hogwarts students.
            // It's a suspending function, so it can be safely called from a coroutine.
            repository.getHogwartsStudents()
                .collectLatest { newsResponse ->
                    // Asynchronously collect the results from the repository and update the LiveData.
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
        Log.d(TAG,"onSearchQueryChanged $newQuery")
        // Update the value of _searchQuery LiveData with the new query
        _searchQuery.value = newQuery
    }

}