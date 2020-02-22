package com.alexdevyatov.pokemonfinder.database

import androidx.room.*
import com.alexdevyatov.pokemonfinder.database.entity.PokemonAbilityEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonStatEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonTypeEntity
import io.reactivex.Single

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonEntity, abilities: List<PokemonAbilityEntity>,
                      stats: List<PokemonStatEntity>, types: List<PokemonTypeEntity>)

    @Delete
    fun deletePokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemons")
    fun loadAllPokemons(): List<PokemonWithTypes>

    @Query("SELECT COUNT(*) FROM pokemons WHERE id = :id")
    fun pokemonsCount(id: Int): Int
}