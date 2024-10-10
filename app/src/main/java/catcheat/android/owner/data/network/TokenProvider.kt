package catcheat.android.owner.data.network

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class TokenProvider @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getToken(): String? {
        val token = sharedPreferences.getString("auth_token", null)
        Log.d("TokenProvider", "Token retrieved: $token")
        return token
    }

    fun saveToken(token: String) {
        Log.d("TokenProvider", "Saving token: $token")
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    fun clearToken() {
        Log.d("TokenProvider", "Clearing token")
        sharedPreferences.edit().remove("auth_token").apply()
    }
}

