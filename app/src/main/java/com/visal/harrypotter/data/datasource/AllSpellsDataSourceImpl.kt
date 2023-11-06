package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.api.AllSpellsApiService
import com.visal.harrypotter.data.model.Spell
import retrofit2.Response
import javax.inject.Inject


/**
 * Implementation of the AllSpellsDataSource interface for fetching information about all spells
 * from an API service.
 *
 * @param apiService The API service used to fetch the spell data.
 */
class AllSpellsDataSourceImpl @Inject constructor(private val apiService: AllSpellsApiService) :
    AllSpellsDataSource {


    /**
     * Fetches a list of all spells from the API service.
     *
     * @return A response containing a list of spells from the Harry Potter universe.
     */
    override suspend fun getAllSpells(): Response<List<Spell>>{
        return  apiService.getAllSpells()
    }

}