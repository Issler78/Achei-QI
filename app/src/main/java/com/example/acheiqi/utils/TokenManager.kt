package com.example.acheiqi.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

object TokenManager {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        val context = context.applicationContext

        val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        // shared preferences cria um arquivo para salvar as infos
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "shared_encrypted_preferences",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }



    fun saveToken(token: String){
        sharedPreferences.edit { putString("TOKEN", token) }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    fun clearToken(){
        sharedPreferences.edit { remove("TOKEN") }
    }
}