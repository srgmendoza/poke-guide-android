package com.sm.poke_data.dto.pokeType

import com.google.gson.annotations.SerializedName
import com.sm.poke_data.dto.pokeReferences.PokemonRefDto

data class PokeTypeDTO(
    val name: String,
    @SerializedName("pokemon")
    val pokemonRefs: List<PokeWithTypeDto>,
)

data class PokeWithTypeDto(
    val pokemon: PokemonRefDto
)