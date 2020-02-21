package com.alexdevyatov.pokemonfinder

import com.alexdevyatov.pokemonfinder.database.PokemonWithTypes
import com.alexdevyatov.pokemonfinder.database.entity.PokemonAbilityEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonStatEntity
import com.alexdevyatov.pokemonfinder.database.entity.PokemonTypeEntity
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.model.PokemonAbility
import com.alexdevyatov.pokemonfinder.model.PokemonStat
import com.alexdevyatov.pokemonfinder.model.PokemonType

fun Pokemon.createEntity() : PokemonEntity = PokemonEntity(id, name, weight, height, sprites)

fun Pokemon.createAbilityEntities() : List<PokemonAbilityEntity> {
    val abilityEntities: MutableList<PokemonAbilityEntity> = mutableListOf()
    for (ability in abilities) {
        abilityEntities.add(PokemonAbilityEntity(null, id, ability.ability))
    }
    return abilityEntities
}

fun Pokemon.createStatEntities() : List<PokemonStatEntity> {
    val statEntities: MutableList<PokemonStatEntity> = mutableListOf()
    for (stat in stats) {
        statEntities.add(PokemonStatEntity(null, id, stat.baseStat, stat.stat))
    }
    return statEntities
}

fun Pokemon.createTypeEntities() : List<PokemonTypeEntity> {
    val typeEntities: MutableList<PokemonTypeEntity> = mutableListOf()
    for (type in types) {
        typeEntities.add(PokemonTypeEntity(null, id, type.slot, type.type))
    }
    return typeEntities
}

fun PokemonWithTypes.createAbilities() : List<PokemonAbility> {
    val result: MutableList<PokemonAbility> = mutableListOf()
    for (abilityEntity in abilities) {
        result.add(PokemonAbility(abilityEntity.ability ))
    }
    return result
}

fun PokemonWithTypes.createStats() : List<PokemonStat> {
    val result: MutableList<PokemonStat> = mutableListOf()
    for (statEntity in stats) {
        result.add(PokemonStat(statEntity.baseStat, statEntity.stat))
    }
    return result
}

fun PokemonWithTypes.createTypes() : List<PokemonType> {
    val result: MutableList<PokemonType> = mutableListOf()
    for (typeEntity in types) {
        result.add(PokemonType(typeEntity.slot, typeEntity.type))
    }
    return result
}

fun PokemonWithTypes.createPokemon() : Pokemon = Pokemon(pokemon.id, pokemon.name, pokemon.weight,
    pokemon.height, createAbilities(), pokemon.sprites, createStats(), createTypes())

fun List<PokemonWithTypes>.createPokemonList() : List<Pokemon> {
    val result: MutableList<Pokemon> = mutableListOf()
    for (pokemonEntity in this) {
        result.add(pokemonEntity.createPokemon())
    }
    return result
}