package com.alexdevyatov.pokemonfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

abstract class BaseViewModel<T> : ViewModel() {

    val data: MutableLiveData<T>
        get() {
            service.submit { this.loadData() }
            return data
        }

    private val service = Executors.newSingleThreadExecutor()

    protected abstract fun loadData()
}