package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Stat

@Entity(tableName = "stats")
data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = true)
    var statId: Long?,

    var pokemonId: Int,

    var baseStat: Int,

    @Embedded
    var stat: Stat
)