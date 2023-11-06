package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.api.AllCharactersApiService
import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of the AllCharactersDataSource interface for fetching information about all characters
 * from an API service.
 *
 * @param apiService The API service used to fetch the character data.
 */
class AllCharactersSourceImpl @Inject constructor(private val apiService: AllCharactersApiService) :
    AllCharactersDataSource {

    /**
     * Fetches a list of all characters from the API service.
     *
     * @return A response containing a list of Harry Potter characters.
     */
    override suspend fun getAllCharacters(): Response<List<HpCharacter>>{
        return  apiService.getAllCharacters()
    }

}