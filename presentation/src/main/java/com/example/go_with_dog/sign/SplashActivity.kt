package com.example.go_with_dog.sign

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.go_with_dog.R
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = SplashViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeActionCommand()
    }

    private fun observeActionCommand() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.actionReceiver.collect {
//            }
//        }
    }
}