package com.alexdevyatov.pokemonfinder.di

import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
    fun inject(pokemonViewModel: PokemonViewModel)
}