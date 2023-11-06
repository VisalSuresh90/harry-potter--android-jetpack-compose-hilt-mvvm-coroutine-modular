package com.visal.harrypotter.data.api

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API service interface for fetching information about Hogwarts staff members.
 */
interface HogwartsStaffApiService {
    /**
     * Fetches a list of Hogwarts staff members from the API.
     *
     * @return A Retrofit Response containing a list of Harry Potter characters representing staff members.
     */
    @GET("/api/characters/staff")
    suspend fun getHogwartsStaff(): Response<List<HpCharacter>>
}
