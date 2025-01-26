package com.sm.poke_domain.models

data class PokeReferenceDomainModel(
    val count: Int,
    val next: String,
    val previous: String?,
    val refs: List<PokeReferenceInfoDomainModel>
)

data class PokeReferenceInfoDomainModel(
    val name: String,
    val url: String
)