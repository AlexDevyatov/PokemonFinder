package com.alexdevyatov.pokemonfinder.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
}