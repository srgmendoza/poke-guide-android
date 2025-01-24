package com.sm.poke_data.api

import com.sm.poke_data.dto.pokeReferences.PokeReferenceDTO
import retrofit2.http.GET

interface PokeApi {
     companion object {
          private const val POKE_LIST_ENDPOINT = "pokemon"
     }

     @GET(POKE_LIST_ENDPOINT)
     suspend fun getPokemonList(): PokeReferenceDTO
}