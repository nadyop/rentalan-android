package com.gdn.rentalan.ui.register.email

import com.gdn.rentalan.ui.base.BaseContract

class RegisterEmailContract {

  interface View: BaseContract.View {
    fun getOtp(otp: String)
  }

  interface Presenter: BaseContract.Presenter {
    fun registerEmail(email: String)
    fun attachView(view: View)
  }
}