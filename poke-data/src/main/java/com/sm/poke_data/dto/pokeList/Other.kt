package com.sm.poke_data.dto.pokeList

import com.google.gson.annotations.SerializedName

data class Other(
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val showdown: Showdown
)