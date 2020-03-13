package com.alexdevyatov.pokemonfinder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

abstract class BaseViewModel<T>(application: Application) : AndroidViewModel(application) {

    var data: MutableLiveData<T> = MutableLiveData()

    private val service = Executors.newSingleThreadExecutor()

    protected abstract fun loadData()
}