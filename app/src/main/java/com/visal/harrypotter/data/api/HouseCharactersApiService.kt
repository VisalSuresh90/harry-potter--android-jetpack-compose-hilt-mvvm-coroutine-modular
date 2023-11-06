package com.visal.harrypotter.data.api



import com.visal.harrypotter.data.model.HpCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API service interface for fetching information about all spells.
 */
interface HouseCharactersApiService {

    @GET("/api/characters/house/{houseName}")
    suspend fun getAllHouseCharacters(@Path("houseName") houseName: String): Response<List<HpCharacter>>

}
