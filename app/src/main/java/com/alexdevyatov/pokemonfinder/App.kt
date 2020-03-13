package com.alexdevyatov.pokemonfinder

import android.app.Application
import androidx.room.Room
import com.alexdevyatov.pokemonfinder.database.AppDatabase
import com.alexdevyatov.pokemonfinder.di.AppComponent
import com.alexdevyatov.pokemonfinder.di.DaggerAppComponent
import com.alexdevyatov.pokemonfinder.di.NetModule

class App : Application() {

    companion object {
        const val POKEMON_IMG_URL = "https://pokeres.bastionbot.org/images/pokemon/"
    }

    private var appComponent: AppComponent? = null
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule())
            .build()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
    }

    fun getAppComponent(): AppComponent? = appComponent

    fun getDatabase(): AppDatabase? = database
}