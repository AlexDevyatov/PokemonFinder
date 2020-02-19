package com.alexdevyatov.pokemonfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

abstract class BaseViewModel<T> : ViewModel() {

    var data: MutableLiveData<T> = MutableLiveData()

    private val service = Executors.newSingleThreadExecutor()

    protected abstract fun loadData()
}