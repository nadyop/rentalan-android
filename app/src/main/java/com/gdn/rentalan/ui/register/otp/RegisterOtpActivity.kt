package com.gdn.rentalan.ui.register.otp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.EditText
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityRegisterOtpBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.util.Router

class RegisterOtpActivity : BaseActivity(), RegisterOtpContract.View {

  private lateinit var binding: ActivityRegisterOtpBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_register_otp)

    validateOtp()
  }

  override fun onBackPressed() {
    super.onBackPressed()
    binding.toolbar.setNavigationOnClickListener {
      Router.goToRegisterEmail(this)
    }
  }

  override fun validateOtp() {

//    binding.toolbar.setOnClickListener {
//      Router.goToRegisterEmail(this)
//      super.onBackPressed()
//    }

    binding.btNext.setOnClickListener {
      val extras = intent?.extras?.getString("otp")
      val string : String = binding.etOtp.text.toString()

      if (string != extras) {
        showSnackbar("OTP tidak sesuai", Snackbar.LENGTH_LONG)
      } else {
        showToast("OTP berhasil dimasukkan", Snackbar.LENGTH_LONG)
        Router.goToLogin(this)
      }
    }

  }

  override fun showProgress(show: Boolean) {
//    nothing implementation
  }

}