package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.api.HouseCharactersApiService
import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of the AllCharactersDataSource interface for fetching information about house characters
 * from an API service.
 *
 * @param apiService The API service used to fetch the character data.
 */
class HouseCharactersSourceImpl @Inject constructor(private val apiService: HouseCharactersApiService) :
   HouseCharactersDataSource {

    /**
     * Fetches a list of all characters from the API service.
     *
     * @return A response containing a list of Harry Potter house characters.
     */
    override suspend fun getAllHouseCharacters(houseName:String): Response<List<HpCharacter>>{
        return  apiService.getAllHouseCharacters(houseName)
    }

}