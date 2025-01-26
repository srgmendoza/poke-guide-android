package com.sm.poke_data.dto.pokeList

data class PokeDataDTO(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val gameIndices: List<GameIndice>,
    val height: Int,
    val heldItems: List<HeldItem>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val pastAbilities: List<Any?>,
    val pastTypes: List<Any?>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)