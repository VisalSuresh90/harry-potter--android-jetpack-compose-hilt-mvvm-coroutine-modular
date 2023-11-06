package com.visal.harrypotter.data.api

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API service interface for fetching information about Hogwarts students.
 */
interface HogwartsStudentsApiService {
    /**
     * Fetches a list of Hogwarts students from the API.
     *
     * @return A Retrofit Response containing a list of Harry Potter characters representing students.
     */
    @GET("/api/characters/students")
    suspend fun getHogwartsStudents(): Response<List<HpCharacter>>
}
