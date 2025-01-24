package com.sm.poke_data.api

import com.sm.poke_data.dto.pokeList.PokeDataDTO
import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    companion object {
        const val NAME_PATH_PARAM = "customerId"
        private const val NAME_PATH_PARAM_BRACKETIZED = "{$NAME_PATH_PARAM}"

        private const val POKE_LIST_ENDPOINT = "pokemon"
        private const val POKE_DETAIL_ENDPOINT = "pokemon/$NAME_PATH_PARAM_BRACKETIZED"
    }

    @GET(POKE_LIST_ENDPOINT)
    suspend fun getPokemonReferenceList(): PokeReferenceDTO

    @GET(POKE_DETAIL_ENDPOINT)
    suspend fun getPokemonDetail(
        @Path(NAME_PATH_PARAM, encoded = true) customerId: String
    ): PokeDataDTO
}