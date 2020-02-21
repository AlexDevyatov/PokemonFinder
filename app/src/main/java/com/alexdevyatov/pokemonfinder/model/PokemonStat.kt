package com.alexdevyatov.pokemonfinder.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stats")
data class PokemonStat(

    @PrimaryKey(autoGenerate = true)
    var statId: Long,

    var pokemonId: Int,

    @SerializedName("base_stat") var baseStat: Int,

    @Embedded
    @SerializedName("stat") var stat: Stat
)