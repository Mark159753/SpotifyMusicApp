package com.example.ui.result_contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.example.common.BuildConfig
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class GetSpotifyAuthCode: ActivityResultContract<Nothing?, String?>() {

    override fun createIntent(context: Context, input: Nothing?): Intent {
        val activity = context as Activity
        val builder = AuthorizationRequest
            .Builder(BuildConfig.ClientId, AuthorizationResponse.Type.CODE, BuildConfig.RedirectUri)
        builder.setScopes(arrayOf(
            "streaming", "playlist-read-private", "user-read-playback-state", "user-read-currently-playing",
            "user-read-playback-position", "user-top-read", "user-read-recently-played", "user-library-read",
            "user-read-private", "user-read-email"
        ))
        val request = builder.build()
        return AuthorizationClient.createLoginActivityIntent(activity, request)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode != Activity.RESULT_OK) {
            return null
        }
        val response = AuthorizationClient.getResponse(resultCode, intent)

        return when(response.type){
            AuthorizationResponse.Type.TOKEN -> response.accessToken
            AuthorizationResponse.Type.CODE -> response.code
            AuthorizationResponse.Type.ERROR -> {
                Log.e(TAG, response.error)
                null
            }
            AuthorizationResponse.Type.EMPTY,
            AuthorizationResponse.Type.UNKNOWN,
            null -> null
        }
    }

    companion object{
        private const val TAG = "GetSpotifyAuthToken"
    }
}