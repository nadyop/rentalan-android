package com.gdn.rentalan.util

import android.content.Context
import android.content.SharedPreferences

class LoginRepository (context: Context){

  private val sharedPreferences: SharedPreferences

//  Call from presenter better -> Log.d("AAAAZSPNIH", loginRepository.userId)
//  fun getUserId(): String? {
//    return sharedPreferences.getString(LoginRepository.USER_ID, "")
//  }
//  fun setUserId(userId: String) {
//    val editor = sharedPreferences.edit()
//    editor.putString(LoginRepository.USER_ID, userId)
//    editor.apply()
//  }

  var userId: String
    get() = sharedPreferences.getString(LoginRepository.USER_ID, "")
    set(userId) = sharedPreferences.edit().putString(LoginRepository.USER_ID, userId).apply()

  var role: String?
    get() = sharedPreferences.getString(LoginRepository.ROLE, "")
    set(role) = sharedPreferences.edit().putString(LoginRepository.ROLE, role).apply()

  init {
    this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
  }

  fun putSharedPref(key: String, value: String) {
    this.sharedPreferences.edit().putString(key, value).apply()
  }

  fun getSharedPref(key: String): String? {
    return this.sharedPreferences.getString(key, null)
  }

  fun clearSharedPrefs() {
    this.sharedPreferences.edit().clear().apply()
  }

  companion object {
    val PREFS_NAME = "Login"
    val USER_ID = "userId"
    val ROLE = "role"
  }
}
