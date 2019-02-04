package com.gdn.rentalan.util

import android.content.Context
import android.content.Intent
import com.gdn.rentalan.ui.main.MainActivity

interface Router {

    fun goToMain(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }
}