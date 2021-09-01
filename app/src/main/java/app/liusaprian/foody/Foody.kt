package app.liusaprian.foody

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import app.liusaprian.foody.network.HttpClient

class Foody : MultiDexApplication() {
    
    companion object {
        private const val TOKEN = "PREF_TOKEN"
        private const val USER = "PREF_USER"

        lateinit var instance : Foody

        fun getApp() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private fun getPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString(TOKEN, token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString(TOKEN, null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString(USER, user).apply()
    }

    fun getUser(): String? {
        return getPreferences().getString(USER, null)
    }
}