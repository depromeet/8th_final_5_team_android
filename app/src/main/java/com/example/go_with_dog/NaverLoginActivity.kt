package com.example.go_with_dog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.go_with_dog.databinding.ActivityNaverLoginBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import timber.log.Timber

class NaverLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNaverLoginBinding
    private lateinit var mOAuthLoginModule: OAuthLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_naver_login)

        binding.naverBtn.setBgResourceId(R.drawable.naver_login_btn_logout)

        mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(
                this
                , getString(R.string.naver_client_id)
                , getString(R.string.naver_client_secret)
                , getString(R.string.naver_client_name)
        )

        binding.naverBtn.setOnClickListener {
            mOAuthLoginModule.startOauthLoginActivity(this, NaverOAuthLoginHandler())
        }

        binding.naverLogoutBtn.setOnClickListener {
            mOAuthLoginModule.logout(this)
        }
    }

    // accessToken이 유효한 경우도 처리해야할까?
    // 공식 문서에는 refreshToken의 유효값만 확인 후 처리하는 것 같은데
    // 확인 필요
    inner class NaverOAuthLoginHandler() : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                val accessToken = mOAuthLoginModule.getAccessToken(this@NaverLoginActivity)
                val refreshToken = mOAuthLoginModule.getRefreshToken(this@NaverLoginActivity)
                val expiresAt = mOAuthLoginModule.getExpiresAt(this@NaverLoginActivity)
                val tokenType = mOAuthLoginModule.getTokenType(this@NaverLoginActivity)

                Timber.d(accessToken)
                Timber.d(refreshToken)
                Timber.d(expiresAt.toString())
                Timber.d(tokenType)

//                startActivity(this@NaverLoginActivity)
            } else {
                val errorCode = mOAuthLoginModule.getLastErrorCode(this@NaverLoginActivity).code
                val errorDesc = mOAuthLoginModule.getLastErrorDesc(this@NaverLoginActivity)

                // 에러발생시 어떻게 처리?

                Timber.d(errorCode)
                Timber.d(errorDesc)
            }
        }
    }
}