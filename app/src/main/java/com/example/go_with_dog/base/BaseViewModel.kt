package com.example.go_with_dog.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class BaseViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }
}