package com.example.go_with_dog

import android.os.Bundle
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var binding: ActivityMainBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}