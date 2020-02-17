package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class PokemonAbility (
    @SerializedName("ability") val ability : Ability
)