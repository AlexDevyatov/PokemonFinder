package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Type

@Entity(tableName = "types")
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = true) val typeId: Long?,

    var pokemonId: Int,

    var slot: Int,

    @Embedded
    var type: Type
)