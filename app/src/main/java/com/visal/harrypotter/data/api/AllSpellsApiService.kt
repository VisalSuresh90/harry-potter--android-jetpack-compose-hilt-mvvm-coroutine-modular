package com.visal.harrypotter.data.api

import com.visal.harrypotter.data.model.Spell
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API service interface for fetching information about all spells.
 */
interface AllSpellsApiService {
    /**
     * Fetches a list of all spells from the API.
     *
     * @return A Retrofit Response containing a list of spells from the Harry Potter universe.
     */
    @GET("/api/spells")
    suspend fun getAllSpells(): Response<List<Spell>>
}
