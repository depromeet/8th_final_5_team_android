package com.example.go_with_dog.sign

import android.content.Context
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.orhanobut.logger.Logger

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

            Logger.d(accessToken)
            Logger.d(refreshToken)
            Logger.d(expiresAt.toString())
            Logger.d(tokenType)
        } else {
            val errorCode = loginModule.getLastErrorCode(context).code
            val errorDesc = loginModule.getLastErrorDesc(context)

            Logger.d(errorCode)
            Logger.d(errorDesc)
        }
    }
}