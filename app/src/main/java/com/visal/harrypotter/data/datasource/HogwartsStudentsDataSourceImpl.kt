package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.api.HogwartsStudentsApiService
import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of the HogwartsStudentsDataSource interface for fetching information about Hogwarts students
 * from an API service.
 *
 * @param apiService The API service used to fetch the Hogwarts student data.
 */
class HogwartsStudentsDataSourceImpl @Inject constructor(private val apiService: HogwartsStudentsApiService) :
    HogwartsStudentsDataSource {

    /**
     * Fetches a list of Hogwarts students from the API service.
     *
     * @return A response containing a list of Harry Potter characters representing students.
     */
    override suspend fun getHogwartsStudents(): Response<List<HpCharacter>>{
        return  apiService.getHogwartsStudents()
    }

}