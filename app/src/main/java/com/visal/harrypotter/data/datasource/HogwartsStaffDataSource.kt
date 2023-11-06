package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response

/**
 * Interface representing a data source for retrieving information about Hogwarts staff members.
 */
interface HogwartsStaffDataSource {

    /**
     * Fetches a list of Hogwarts staff members.
     *
     * @return A response containing a list of Harry Potter characters representing staff members.
     */
    suspend fun getHogwartsStaff(): Response<List<HpCharacter>>
}