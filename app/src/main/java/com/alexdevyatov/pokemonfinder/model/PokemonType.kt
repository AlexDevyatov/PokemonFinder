package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Type
)