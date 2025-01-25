package com.sm.poke_data.dto.pokeList

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?,
    val frontShiny: String
)