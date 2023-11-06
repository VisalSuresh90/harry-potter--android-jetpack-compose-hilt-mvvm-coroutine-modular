package com.visal.harrypotter.data.datasource

import com.visal.harrypotter.data.model.Spell
import retrofit2.Response

/**
 * Interface representing a data source for retrieving information about all spells.
 */
interface AllSpellsDataSource {


    /**
     * Fetches a list of all spells.
     *
     * @return A response containing a list of spells from the Harry Potter universe.
     */
    suspend fun getAllSpells(): Response<List<Spell>>
}