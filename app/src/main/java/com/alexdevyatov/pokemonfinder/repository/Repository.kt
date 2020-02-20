package com.alexdevyatov.pokemonfinder.repository

import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.service.ApiService
import io.reactivex.Single

class Repository(private val service: ApiService) {

    fun getPokemon(name: String): Single<Pokemon> {
        return service.getPokemon(name)
    }

    fun getPokemon(id: Int): Single<Pokemon> {
        return service.getPokemon(id)
    }

}