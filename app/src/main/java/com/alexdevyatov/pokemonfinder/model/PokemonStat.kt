package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonStat(

    @SerializedName("base_stat") var baseStat: Int,

    @SerializedName("stat") var stat: Stat
)