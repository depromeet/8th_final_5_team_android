package com.example.go_with_dog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.go_with_dog.databinding.ActivityLoginBinding
import com.nhn.android.naverlogin.OAuthLogin

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val mOAuthLoginModule: OAuthLogin by lazy {
        OAuthLogin.getInstance().apply {
            init(
                    this@LoginActivity
                    , getString(R.string.naver_client_id)
                    , getString(R.string.naver_client_secret)
                    , getString(R.string.naver_client_name)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    fun startNaverLogin(view: View) {
        mOAuthLoginModule.startOauthLoginActivity(
                this,
                NaverOAuthLoginHandler(mOAuthLoginModule,
                        this))
    }
}