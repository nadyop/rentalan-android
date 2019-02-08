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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_splashscreen)

    Handler().postDelayed({
      Router.goToLogin(this)
    }, 3000)
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.progressBar.visibility = View.GONE
    }
  }

}