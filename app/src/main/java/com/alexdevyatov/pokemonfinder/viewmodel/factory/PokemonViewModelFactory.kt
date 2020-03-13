package com.alexdevyatov.pokemonfinder.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexdevyatov.pokemonfinder.di.AppComponent
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel

class PokemonViewModelFactory(
    private val appComponent: AppComponent,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
        if (modelClass == PokemonViewModel::class.java) {
            return PokemonViewModel(appComponent, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}