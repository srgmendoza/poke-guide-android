package com.sm.poke_data.api

import com.sm.poke_data.dto.pokeList.PokeDataDTO
import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import com.sm.poke_data.dto.pokeType.PokeTypeDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    companion object {
        const val NAME_PATH_PARAM = "name"
        private const val NAME_PATH_PARAM_BRACKETIZED = "{$NAME_PATH_PARAM}"

        const val TYPE_ID_PATH_PARAM = "typeId"
        private const val TYPE_ID_PATH_PARAM_BRACKETIZED = "{$TYPE_ID_PATH_PARAM}"

        private const val POKE_LIST_ENDPOINT = "pokemon"
        private const val POKE_DETAIL_ENDPOINT = "pokemon/$NAME_PATH_PARAM_BRACKETIZED"
        private const val POKE_TYPE_ENDPOINT = "type/$TYPE_ID_PATH_PARAM_BRACKETIZED"
    }

    @GET(POKE_LIST_ENDPOINT)
    suspend fun getPokemonReferenceList(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): PokeReferenceDTO

    @GET(POKE_DETAIL_ENDPOINT)
    suspend fun getPokemonDetail(
        @Path(NAME_PATH_PARAM, encoded = true) customerId: String
    ): PokeDataDTO

    @GET(POKE_TYPE_ENDPOINT)
    suspend fun getPokemonType(
        @Path(TYPE_ID_PATH_PARAM, encoded = true) typeId: String
    ): PokeTypeDTO
}