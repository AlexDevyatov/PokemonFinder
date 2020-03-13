package com.alexdevyatov.pokemonfinder.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.alexdevyatov.pokemonfinder.App
import com.alexdevyatov.pokemonfinder.di.AppComponent
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.repository.Repository
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonViewModel(appComponent: AppComponent, application: Application) : BaseViewModel<Pokemon>(
    application
) {

    @Inject
    lateinit var repository: Repository

    var name = ""
        @SuppressLint("CheckResult")
        set(value) {
            repository
                .getPokemon(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onSuccess = {
                        Log.d("REQUEST", "Success")
                        Glide.with((getApplication() as App).applicationContext)
                            .load(App.POKEMON_IMG_URL + "${it.id}.png")
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .preload()

                        data.postValue(it)
                    },
                    onError = {
                        Log.d("REQUEST", "Error")
                        data.postValue(null)
                    }
                )
        }

    var id = 0
        @SuppressLint("CheckResult")
        set(value) {
            repository
                .getPokemon(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onSuccess = {
                        Log.d("REQUEST", "Success")
                        Glide.with((getApplication() as App).applicationContext)
                            .load(App.POKEMON_IMG_URL + "${it.id}.png")
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .preload()

                        data.postValue(it)
                    },
                    onError = {
                        Log.d("REQUEST", "Error")
                        data.postValue(null)
                    }
                )
        }

    init {
        appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun loadData() {
        repository
            .getPokemon(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("REQUEST", "Success")
                    data.postValue(it)
                },
                onError = {
                    Log.d("REQUEST", "Error")
                    data.postValue(null)
                }
            )
    }
}