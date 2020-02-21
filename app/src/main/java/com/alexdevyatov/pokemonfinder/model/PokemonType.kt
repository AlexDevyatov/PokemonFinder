package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot")
    var slot: Int,

    @SerializedName("type")
    var type: Type
)