package com.sm.poke_data.dto.pokeList

data class Sprites(
    val backDefault: String,
    val backFemale: Any,
    val backShiny: String,
    val backShinyFemale: Any,
    val frontDefault: String,
    val frontFemale: Any,
    val frontShiny: String,
    val frontShinyFemale: Any,
    val other: Other,
    val versions: Versions
)