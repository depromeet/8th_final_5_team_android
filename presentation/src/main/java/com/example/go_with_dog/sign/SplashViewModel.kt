package com.example.go_with_dog.sign

import androidx.lifecycle.viewModelScope
import com.example.go_with_dog.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashViewModel.Action>() {
    sealed class Action(

    )

    init {
        viewModelScope.launch {
            delay(3000L)

        }
    }
}