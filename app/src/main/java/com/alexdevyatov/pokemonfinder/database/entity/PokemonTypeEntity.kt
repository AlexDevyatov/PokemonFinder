package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Type

@Entity(
    tableName = "types", foreignKeys = [ForeignKey(
        entity = PokemonEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pokemonId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = true) val typeId: Long?,

    var pokemonId: Int,

    var slot: Int,

    @Embedded
    var type: Type
)