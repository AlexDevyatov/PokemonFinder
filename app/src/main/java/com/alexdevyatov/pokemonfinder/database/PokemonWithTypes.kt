package com.alexdevyatov.pokemonfinder.database

import androidx.room.Embedded
import androidx.room.Relation
import com.alexdevyatov.pokemonfinder.database.entity.PokemonAbilityEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonStatEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonTypeEntity

data class PokemonWithTypes(

    @Embedded
    val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val types: List<PokemonTypeEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<PokemonStatEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val abilities: List<PokemonAbilityEntity>

)