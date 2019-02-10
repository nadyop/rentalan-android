package com.gdn.rentalan.ui.splashscreen

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivitySplashscreenBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.util.Router

class SplashScreenActivity : BaseActivity(), SplashScreenContract.View {

  private lateinit var binding: ActivitySplashscreenBinding
  val SPLASH_TIMEOUT: Long = 3000
  private var role = ""
  private var userId = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_splashscreen)

    if (userId != ""){
      goToActivity(role, userId)
    } else {
      Handler().postDelayed({
        Router.goToLogin(this)
      }, SPLASH_TIMEOUT)
    }
  }

  override fun goToActivity(role: String, userId: String) {
    this.role = role
    this.userId = userId
    if (role == "admin") {
      Handler().postDelayed({
        Router.goToMain(this)
      }, SPLASH_TIMEOUT)
    } else {
      Handler().postDelayed({
        Router.goToUserMain(this)
      }, SPLASH_TIMEOUT)
    }
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.progressBar.visibility = View.GONE
    }
  }

}