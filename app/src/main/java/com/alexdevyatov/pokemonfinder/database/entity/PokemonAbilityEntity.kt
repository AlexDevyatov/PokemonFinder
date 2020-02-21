package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.Ability

@Entity(tableName = "abilities")
data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = true)
    var abilityId: Long?,

    var pokemonId: Int,

    @Embedded
    var ability: Ability
)