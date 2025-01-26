package com.sm.poke_domain.models

data class PokemonListByTypeDomainModel(
    val name: String,
    val topRelatedPokemons: List<PokeReferenceInfoDomainModel>
)