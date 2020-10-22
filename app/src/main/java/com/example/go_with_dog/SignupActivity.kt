package com.example.go_with_dog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.go_with_dog.databinding.ActivitySignupBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import timber.log.Timber

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val mOAuthLoginModule: OAuthLogin by lazy {
        OAuthLogin.getInstance().apply {
            init(
                    this@SignupActivity
                    , getString(R.string.naver_client_id)
                    , getString(R.string.naver_client_secret)
                    , getString(R.string.naver_client_name)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
    }

    fun startNaverLogin(view: View) {
        mOAuthLoginModule.startOauthLoginActivity(
                this,
                NaverOAuthLoginHandler(mOAuthLoginModule,
                        this))
    }
}