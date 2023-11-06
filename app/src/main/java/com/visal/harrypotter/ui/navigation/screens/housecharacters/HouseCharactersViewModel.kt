package com.visal.harrypotter.ui.navigation.screens.housecharacters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visal.harrypotter.data.model.House
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.data.repository.HouseCharactersRepository
import com.visal.harrypotter.ui.common.util.AppConstant
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
class HouseCharactersViewModel @Inject constructor(private val repository: HouseCharactersRepository) :
    ViewModel() {

    companion object {
        const val TAG = "HouseCharactersViewModel"
    }

    // Define a private mutable StateFlow to hold a list of Harry Potter house characters
    private val _hpCharacters: MutableStateFlow<ResourceState<List<HpCharacter>>> =
        MutableStateFlow(ResourceState.Loading());

    // Define a public StateFlow to expose Harry Potter house characters data
    val hpCharacters: StateFlow<ResourceState<List<HpCharacter>>> = _hpCharacters

    // Define a private mutable StateFlow to hold the search query
    private val _searchQuery = MutableStateFlow("")

    // Define a StateFlow to hold the search query string
    val searchQuery: StateFlow<String> = _searchQuery



    private val _houseList = MutableStateFlow(listOf(
        House(AppConstant.GRYFFINDOR_ID, AppConstant.GRYFFINDOR, AppConstant.GRYFFINDOR_IMAGE),
        House(AppConstant.SLYTHERIN_ID, AppConstant.SLYTHERIN, AppConstant.SLYTHERIN_IMAGE),
        House(AppConstant.HUFFLEPUFF_ID, AppConstant.HUFFLEPUFF, AppConstant.HUFFLEPUFF_IMAGE),
        House(AppConstant.RAVENCLAW_ID, AppConstant.RAVENCLAW, AppConstant.RAVENCLAW_IMAGE),
    ))
    val houseList: StateFlow<List<House>> = _houseList


    init {
        Log.d(TAG, "init called")
//        getHouseCharacters()
    }


    /**
     * Function for fetch All Harry Potter House Characters data from server
     */
     fun getHouseCharacters(houseName:String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllHouseCharacters(houseName)
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
        Log.d(HogwartsStudentsViewModel.TAG,"onSearchQueryChanged $newQuery")
        // Update the value of _searchQuery LiveData with the new query
        _searchQuery.value = newQuery
    }

}