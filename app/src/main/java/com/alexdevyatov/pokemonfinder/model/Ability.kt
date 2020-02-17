package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("name") val name : String
)
