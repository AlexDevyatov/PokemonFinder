package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("name") val name: String
)