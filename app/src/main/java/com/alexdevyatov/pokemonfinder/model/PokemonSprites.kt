package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonSprites(
    @SerializedName("back_default") val back : String?,
    @SerializedName("front_default") val front : String?
)