package com.gdn.rentalan.ui.splashscreen

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivitySplashscreenBinding
import com.gdn.rentalan.ui.base.BaseActivity

class SplashScreenActivity : BaseActivity(), SplashScreenContract.View {

  private lateinit var binding: ActivitySplashscreenBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_splashscreen)
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.progressBar.visibility = View.GONE
    }
  }

}