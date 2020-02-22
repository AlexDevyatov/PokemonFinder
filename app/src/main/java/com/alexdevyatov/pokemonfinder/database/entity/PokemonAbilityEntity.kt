package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Ability

@Entity(
    tableName = "abilities", foreignKeys = [ForeignKey(
        entity = PokemonEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pokemonId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = true)
    var abilityId: Long?,

    var pokemonId: Int,

    @Embedded
    var ability: Ability
)