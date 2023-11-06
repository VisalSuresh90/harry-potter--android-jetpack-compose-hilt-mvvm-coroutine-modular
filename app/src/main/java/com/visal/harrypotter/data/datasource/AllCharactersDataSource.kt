package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
/**
 * Interface representing a data source for retrieving information about all characters.
 */
interface AllCharactersDataSource {
    /**
     * Fetches a list of all characters.
     *
     * @return A response containing a list of Harry Potter characters.
     */
    suspend fun getAllCharacters(): Response<List<HpCharacter>>
}