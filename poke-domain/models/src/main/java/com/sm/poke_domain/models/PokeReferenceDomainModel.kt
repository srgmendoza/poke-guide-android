package com.sm.poke_domain.models

data class PokeReferenceDomainModel(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<ResultDomainModel>
)

data class ResultDomainModel(
    val name: String,
    val url: String
)