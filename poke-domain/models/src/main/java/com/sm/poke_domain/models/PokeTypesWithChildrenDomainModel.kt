package com.sm.poke_domain.models

data class PokeTypesWithChildrenDomainModel(
    val type: PokeTypeDomainModel,
    val children: List<PokeDetailDomainModel>
)
