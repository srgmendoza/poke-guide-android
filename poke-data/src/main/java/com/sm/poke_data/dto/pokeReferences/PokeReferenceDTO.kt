package com.sm.poke_data.dto.pokeReferences

data class PokeReferenceDTO(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)