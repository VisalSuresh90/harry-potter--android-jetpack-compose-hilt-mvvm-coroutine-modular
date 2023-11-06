package com.visal.harrypotter.data.api

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API service interface for fetching information about all characters.
 */
interface AllCharactersApiService {
    /**
     * Fetches a list of all characters from the API.
     *
     * @return A Retrofit Response containing a list of Harry Potter characters.
     */
    @GET("/api/characters")
    suspend fun getAllCharacters(): Response<List<HpCharacter>>
}
