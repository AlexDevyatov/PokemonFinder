package com.alexdevyatov.pokemonfinder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexdevyatov.pokemonfinder.database.entity.PokemonAbilityEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonStatEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonTypeEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonEntity, abilities: List<PokemonAbilityEntity>,
                      stats: List<PokemonStatEntity>, types: List<PokemonTypeEntity>)

    @Query("SELECT * FROM pokemons")
    fun loadAllPokemons(): List<PokemonWithTypes>
}