package com.gdn.rentalan.ui.splashscreen

import com.gdn.rentalan.ui.base.BaseContract

class SplashScreenContract {

  interface View : BaseContract.View {
    fun goToActivity(role: String, userId: String)
  }

  interface Presenter : BaseContract.Presenter {
    fun attachView(view: View)
    fun loadUserInfo()
  }
}