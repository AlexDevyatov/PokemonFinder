package com.alexdevyatov.pokemonfinder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.model.PokemonAbility
import com.alexdevyatov.pokemonfinder.model.PokemonStat
import com.alexdevyatov.pokemonfinder.model.PokemonType

@Database(entities = [Pokemon::class, PokemonAbility::class, PokemonStat::class, PokemonType::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}