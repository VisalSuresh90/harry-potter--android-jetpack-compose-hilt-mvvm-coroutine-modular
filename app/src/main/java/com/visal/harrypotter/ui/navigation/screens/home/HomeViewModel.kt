package com.visal.harrypotter.ui.navigation.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visal.harrypotter.data.model.HomeMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    val imageUrls = listOf(
        "https://images3.alphacoders.com/178/178309.jpg",
        "https://images8.alphacoders.com/509/509213.jpg",
        "https://images4.alphacoders.com/556/556449.jpg",
        "https://images6.alphacoders.com/853/853375.png",
        "https://images4.alphacoders.com/962/96278.jpg"
    )

    val menuList = listOf(
        HomeMenu(name = "All Characters", image = "https://images3.alphacoders.com/178/178309.jpg"),
        HomeMenu(
            name = "Hogwarts Students",
            image = "https://images8.alphacoders.com/509/509213.jpg"
        ),
        HomeMenu(
            name = " Hogwarts Staff",
            image = "https://images4.alphacoders.com/556/556449.jpg"
        ),
        HomeMenu(name = "All Spells", image = "https://images4.alphacoders.com/962/96278.jpg"),
        HomeMenu(name = "Houses", image = "https://keepthescore.com/static/images/blog_images/hogwarts-houses.jpg")
    )

    // LiveData to observe the current value
    //val currentImage = MutableLiveData<String>()
    private val _currentImage = MutableStateFlow("")
    val currentImage: StateFlow<String> = _currentImage


    /**
     * Function for updating the new image from the list to current image in specific time interval
     */
    fun startUpdatingValue(
        timeMilliSec: Long
    ) {
        // Start a coroutine within the viewModelScope to continuously update a value
        viewModelScope.launch {
            while (true) {
                updateValue() // Call the updateValue function
                delay(timeMilliSec) // Delay for the specified time in milliseconds
            }
        }
    }


    /**
     * Function that update the new image to the current image
     */
    private suspend fun updateValue() {
        // Perform the following operations within a coroutine with Dispatchers.Default context
        withContext(Dispatchers.Default) {
            // Get the next value from some function (assumed to be a suspend function)
            val nextValue = getNextValue()
            // Update the value of the '_currentImage' LiveData using 'value' property
            _currentImage.value = nextValue
        }
    }


    /**
     * Function that return the next value from the 'imageUrls' list based on the calculated index
     */
    private fun getNextValue(): String {
        // Find the index of the current image value within the 'imageUrls' list
        val currentIndex = imageUrls.indexOf(currentImage.value)
        // Calculate the index of the next value, wrapping around to 0 if the current value is the last in the list
        val nextIndex = if (currentIndex == imageUrls.size - 1) 0 else currentIndex + 1
        return imageUrls[nextIndex]
    }


}