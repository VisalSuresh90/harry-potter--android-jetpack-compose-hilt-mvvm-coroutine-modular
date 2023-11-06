package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response

/**
 * Interface representing a data source for retrieving information about Hogwarts students.
 */
interface HogwartsStudentsDataSource {


    /**
     * Fetches a list of Hogwarts students.
     *
     * @return A response containing a list of Harry Potter characters representing students.
     */
    suspend fun getHogwartsStudents(): Response<List<HpCharacter>>
}