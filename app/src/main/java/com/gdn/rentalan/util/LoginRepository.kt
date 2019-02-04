package com.gdn.rentalan.util

import android.content.Context
import android.content.SharedPreferences

class LoginRepository {

    private var instance: LoginRepository? = null
    private lateinit var sharedPreferences: SharedPreferences

    private fun LoginRepository(context: Context) {
        this.sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)
    }

    fun getInstance(context: Context): LoginRepository? {
        if (instance == null) {
            instance = this
        }
        return instance
    }
}