package com.alexdevyatov.pokemonfinder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("abilities") val abilities: @RawValue List<PokemonAbility>,
    @SerializedName("sprites") val sprites: @RawValue PokemonSprites,
    @SerializedName("stats") val stats: @RawValue List<PokemonStat>,
    @SerializedName("types") val types: @RawValue List<PokemonType>
) : Parcelable