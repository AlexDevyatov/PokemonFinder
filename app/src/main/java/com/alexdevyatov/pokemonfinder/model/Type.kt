package com.alexdevyatov.pokemonfinder.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("name") val name: String
)