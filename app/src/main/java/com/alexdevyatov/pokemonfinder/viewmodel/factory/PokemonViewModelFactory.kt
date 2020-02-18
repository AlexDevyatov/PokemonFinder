package com.alexdevyatov.pokemonfinder.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexdevyatov.pokemonfinder.di.AppComponent
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel

class PokemonViewModelFactory(private val appComponent: AppComponent) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
        if (modelClass == PokemonViewModel::class.java) {
           return PokemonViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}