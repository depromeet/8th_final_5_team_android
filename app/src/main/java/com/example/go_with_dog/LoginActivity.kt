package com.example.go_with_dog

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityLoginBinding
import com.nhn.android.naverlogin.OAuthLogin

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this).get(LoginViewModel::class.java)

    private val mOAuthLoginModule: OAuthLogin by lazy {
        OAuthLogin.getInstance().apply {
            init(
                    this@LoginActivity,
                    getString(R.string.naver_client_id),
                    getString(R.string.naver_client_secret),
                    getString(R.string.naver_client_name)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.viewModel = viewModel
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.doNaverLogin.observe(this, Observer {
            startNaverLogin()
        })
    }

    fun startNaverLogin() {
        mOAuthLoginModule.startOauthLoginActivity(
                this, NaverOAuthLoginHandler(mOAuthLoginModule, this)
        )
    }
}