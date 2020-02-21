package com.alexdevyatov.pokemonfinder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Pokemon(

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("weight")
    var weight: Int = 0,

    @SerializedName("height")
    var height: Int = 0,

    @SerializedName("abilities")
    var abilities: @RawValue List<PokemonAbility> = emptyList(),

    @SerializedName("sprites")
    var sprites: @RawValue PokemonSprites = PokemonSprites("", ""),

    @SerializedName("stats")
    var stats: @RawValue List<PokemonStat> = emptyList(),

    @SerializedName("types")
    var types: @RawValue List<PokemonType> = emptyList()
) : Parcelable