package com.visal.harrypotter.data.repository

import android.util.Log
import com.visal.harrypotter.data.datasource.HogwartsStudentsDataSource
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class for managing and accessing Hogwarts students data.
 *
 * @param dataSource The data source responsible for retrieving Hogwarts students data.
 */
class HogwartsStudentsRepository @Inject constructor(private val dataSource: HogwartsStudentsDataSource){

    companion object{
        private val TAG="HogwartsStudentsRepository"
    }

    /**
     * Retrieves a list of Hogwarts students using a Flow and emits ResourceState values.
     *
     * @return A Flow of ResourceState containing the list of Hogwarts students.
     */
    suspend fun getHogwartsStudents(): Flow<ResourceState<List<HpCharacter>>> {
        return flow {
            // Emit a Loading state while data is being fetched
            emit(ResourceState.Loading())
            // Fetch the data from the data source
            val response = dataSource.getHogwartsStudents()
            // Check if the response is successful and contains data
            if (response.isSuccessful && response.body() != null) {
                // Emit a Success state with the fetched data
                emit(ResourceState.Success(response.body()!!))
            } else {
                // Emit an Error state with an error message
                emit(ResourceState.Error("Error while fetching News Headlines"))
            }
        }.catch { e ->
            // Handle exceptions and emit an Error state with an error message
            Log.d(TAG,"Error ${e.localizedMessage ?: "Error in flow"}")
            emit(ResourceState.Error(e.localizedMessage ?: "Error in flow"))
        }
    }

}