package com.alexdevyatov.pokemonfinder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexdevyatov.pokemonfinder.database.entity.PokemonAbilityEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonStatEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonTypeEntity

@Database(entities = [PokemonEntity::class, PokemonAbilityEntity::class, PokemonStatEntity::class, PokemonTypeEntity::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}