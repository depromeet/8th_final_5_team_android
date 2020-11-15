package com.example.go_with_dog.sign

import com.example.go_with_dog.base.BaseViewModel
import com.example.go_with_dog.util.SingleLiveEvent

class LoginViewModel: BaseViewModel() {
    val doNaverLogin: SingleLiveEvent<Void> = SingleLiveEvent()

    fun onClickNaverLogin() {
        doNaverLogin.call()
    }
}