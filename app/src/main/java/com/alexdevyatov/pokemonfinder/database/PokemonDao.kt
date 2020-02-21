package com.alexdevyatov.pokemonfinder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexdevyatov.pokemonfinder.model.*

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon, abilities: List<PokemonAbility>,
                      stats: List<PokemonStat>, types: List<PokemonType>)

    @Query("SELECT * FROM pokemons")
    fun loadAllPokemons(): Array<PokemonWithTypes>
}