package com.example.go_with_dog

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)
}