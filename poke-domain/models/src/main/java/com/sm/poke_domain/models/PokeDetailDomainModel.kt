package com.sm.poke_domain.models

data class PokeDetailDomainModel(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val soundUrl: String,
    val height: Int,
    val weight: Int,
    val topType: PokeTypeDomainModel,
    val relatedPokemons: List<PokeDetailDomainModel> = listOf()
)

data class PokeTypeDomainModel(
    val name: String?,
    val refId: String?
)