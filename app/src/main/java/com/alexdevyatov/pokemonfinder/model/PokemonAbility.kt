package com.alexdevyatov.pokemonfinder.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "entities")
data class PokemonAbility (

    @PrimaryKey(autoGenerate = true)
    var abilityId: Long,

    var pokemonId: Int,

    @Embedded
    @SerializedName("ability")
    var ability : Ability
)