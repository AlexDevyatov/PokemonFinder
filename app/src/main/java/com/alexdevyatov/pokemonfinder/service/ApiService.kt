package com.alexdevyatov.pokemonfinder.service

import com.alexdevyatov.pokemonfinder.model.Pokemon
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{name}")
    fun getPokemon(
        @Path("name") name: String
    ) : Single<Pokemon>
}