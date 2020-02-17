package com.alexdevyatov.pokemonfinder

import android.app.Application
import com.alexdevyatov.pokemonfinder.di.AppComponent
import com.alexdevyatov.pokemonfinder.di.DaggerAppComponent
import com.alexdevyatov.pokemonfinder.di.NetModule

class App : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule())
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }
}