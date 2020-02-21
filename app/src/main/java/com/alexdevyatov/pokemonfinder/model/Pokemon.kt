package com.alexdevyatov.pokemonfinder.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.lang.reflect.Constructor

@Parcelize
@Entity(tableName = "pokemons")
data class Pokemon(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("weight")
    var weight: Int = 0,

    @SerializedName("height")
    var height: Int = 0,

    @Ignore
    @SerializedName("abilities")
    var abilities: @RawValue List<PokemonAbility> = emptyList(),

    @Ignore
    @SerializedName("sprites")
    //@Embedded
    var sprites: @RawValue PokemonSprites = PokemonSprites("", ""),

    @Ignore
    @SerializedName("stats")
    var stats: @RawValue List<PokemonStat> = emptyList(),

    @Ignore
    @SerializedName("types")
    var types: @RawValue List<PokemonType> = emptyList()
) : Parcelable