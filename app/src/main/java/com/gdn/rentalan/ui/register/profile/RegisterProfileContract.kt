package com.gdn.rentalan.ui.register.profile

import com.gdn.rentalan.ui.base.BaseContract

class RegisterProfileContract {

  interface View : BaseContract.View {
    fun validateUsernameEmail(code: Int)
  }

  interface Presenter : BaseContract.Presenter {
    fun registerProfile(username: String, email: String, phoneNumber: String, password: String,
        sureName: String)

    fun attachView(view: View)
  }
}