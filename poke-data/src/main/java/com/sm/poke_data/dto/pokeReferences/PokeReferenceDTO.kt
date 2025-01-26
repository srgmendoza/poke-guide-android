package com.sm.poke_data.dto.pokeReferences

import com.google.gson.annotations.SerializedName

data class PokeReferenceDTO(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerializedName("results")
    val pokemonRefDtos: List<PokemonRefDto>
)