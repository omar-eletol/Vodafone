package com.task.data.datasource.pref


import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class Pref @Inject constructor(
    private val context: Context, isDebug: Boolean, name: String
) {
    private val masterKey by lazy {
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

    private val mode by lazy { Context.MODE_PRIVATE }

    private val preferences: SharedPreferences by lazy {
        if (isDebug) context.getSharedPreferences(name, mode)
        else EncryptedSharedPreferences.create(
            context,
            name,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    private val editor by lazy { preferences.edit() }

    fun getValue(key: String): String? = preferences.getString(key, null)
    fun setValue(key: String, value: String?) = editor.putString(key, value).apply()

    companion object {
        const val Token_KEY = "Token_KEY"


    }
}
