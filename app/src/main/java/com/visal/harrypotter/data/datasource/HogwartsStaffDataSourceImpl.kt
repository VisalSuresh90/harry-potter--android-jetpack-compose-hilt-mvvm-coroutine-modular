package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.api.HogwartsStaffApiService
import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of the HogwartsStaffDataSource interface for fetching information about Hogwarts staff members
 * from an API service.
 *
 * @param apiService The API service used to fetch the Hogwarts staff member data.
 */
class HogwartsStaffDataSourceImpl @Inject constructor(private val apiService: HogwartsStaffApiService) :
    HogwartsStaffDataSource {

    /**
     * Fetches a list of Hogwarts staff members from the API service.
     *
     * @return A response containing a list of Harry Potter characters representing staff members.
     */override suspend fun getHogwartsStaff(): Response<List<HpCharacter>>{
        return  apiService.getHogwartsStaff()
    }

}