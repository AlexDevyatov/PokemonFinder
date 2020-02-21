package com.alexdevyatov.pokemonfinder.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "types")
data class PokemonType(
    @PrimaryKey(autoGenerate = true) val typeId: Long,

    var pokemonId: Int,

    @SerializedName("slot")
    var slot: Int,

    @Embedded
    @SerializedName("type")
    var type: Type
)