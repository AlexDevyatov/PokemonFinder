package com.alexdevyatov.pokemonfinder.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexdevyatov.pokemonfinder.model.PokemonSprites

@Entity(tableName = "pokemons")
data class PokemonEntity (

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    var name: String = "",

    var weight: Int = 0,

    var height: Int = 0,

    @Embedded
    var sprites: PokemonSprites = PokemonSprites("", "")
)