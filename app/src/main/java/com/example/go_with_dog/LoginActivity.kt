package com.example.go_with_dog

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityLoginBinding
import com.nhn.android.naverlogin.OAuthLogin

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val binding: ActivityLoginBinding by lazy {
        getDataBinding()
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(LoginViewModel::class.java)
    }

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

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        setObserveLiveData()
    }

    private fun setObserveLiveData() {
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