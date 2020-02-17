package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: Stat
)