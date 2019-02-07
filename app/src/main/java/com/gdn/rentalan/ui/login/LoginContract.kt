package com.gdn.rentalan.ui.login

import com.gdn.rentalan.ui.base.BaseContract

class LoginContract {

  interface View : BaseContract.View {
    fun goToMain()
  }

  interface Presenter : BaseContract.Presenter {
    fun loginData(username: String, password: String)
    fun attachView(view: LoginContract.View)
  }

}