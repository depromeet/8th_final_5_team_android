package com.example.go_with_dog.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>: AppCompatActivity(){
    private lateinit var dataBinding: T

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()
    }

    fun getDataBinding(): T {
        return dataBinding
    }
}
