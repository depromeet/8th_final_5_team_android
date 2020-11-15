package com.example.go_with_dog.sign

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.go_with_dog.R
import com.example.go_with_dog.base.BaseActivity
import com.example.go_with_dog.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.nhn.android.naverlogin.OAuthLogin
import com.orhanobut.logger.Logger

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutId: Int = R.layout.activity_login
    override val viewModel: LoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

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

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Logger.e("로그인 실패", error)
        } else if (token != null) {
            Logger.i("로그인 성공 ${token.accessToken}")
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

    fun loginKakao() {
        if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
            LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
}