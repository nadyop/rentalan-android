package com.gdn.rentalan.ui.register.otp

import com.gdn.rentalan.ui.base.BaseContract

class RegisterOtpContract {

  interface View: BaseContract.View {
    fun validateOtp()
  }

  interface Presenter: BaseContract.Presenter {
  }
}