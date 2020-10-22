package com.example.go_with_dog

import android.content.Context
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import timber.log.Timber

class NaverOAuthLoginHandler(
    private val loginModule: OAuthLogin,
    private val context: Context
) : OAuthLoginHandler() {
    override fun run(success: Boolean) {
        if (success) {
            val accessToken = loginModule.getAccessToken(context)
            val refreshToken = loginModule.getRefreshToken(context)
            val expiresAt = loginModule.getExpiresAt(context)
            val tokenType = loginModule.getTokenType(context)

            Toast.makeText(context, "액세스 토큰: " + accessToken.toString(), Toast.LENGTH_LONG).show()

            Timber.d(accessToken)
            Timber.d(refreshToken)
            Timber.d(expiresAt.toString())
            Timber.d(tokenType)

            // SharedPreference에 토큰 값 입력하는 코드 추가 필요

//                startActivity()
        } else {
            val errorCode = loginModule.getLastErrorCode(context).code
            val errorDesc = loginModule.getLastErrorDesc(context)

            Timber.d(errorCode)
            Timber.d(errorDesc)
        }
    }
}