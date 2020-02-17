package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("abilities") val abilities: List<PokemonAbility>,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("stats") val stats: List<PokemonStat>,
    @SerializedName("types") val types: List<PokemonType>
)