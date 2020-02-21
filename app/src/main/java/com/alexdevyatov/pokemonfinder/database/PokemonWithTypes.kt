package com.alexdevyatov.pokemonfinder.database

import androidx.room.Embedded
import androidx.room.Relation
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.model.PokemonAbility
import com.alexdevyatov.pokemonfinder.model.PokemonStat
import com.alexdevyatov.pokemonfinder.model.PokemonType

data class PokemonWithTypes(

    @Embedded
    val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val types: List<PokemonType>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<PokemonStat>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val abilities: List<PokemonAbility>

)