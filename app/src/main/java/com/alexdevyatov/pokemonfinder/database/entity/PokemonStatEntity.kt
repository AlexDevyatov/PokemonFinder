package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Stat

@Entity(
    tableName = "stats", foreignKeys = [ForeignKey(
        entity = PokemonEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pokemonId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PokemonStatEntity(
    @PrimaryKey(autoGenerate = true)
    var statId: Long?,

    var pokemonId: Int,

    var baseStat: Int,

    @Embedded
    var stat: Stat
)