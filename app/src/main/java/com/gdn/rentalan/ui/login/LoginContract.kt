package com.gdn.rentalan.ui.login

import com.gdn.rentalan.ui.base.BaseContract

class LoginContract {

  interface View : BaseContract.View {
    fun validate(code: Int)
    fun goToMainPage(role: String)
  }

  interface Presenter : BaseContract.Presenter {
    fun login(username: String, password: String)
    fun loadUserInfo()
    fun attachView(view: LoginContract.View)
  }

}