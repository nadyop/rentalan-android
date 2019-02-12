package com.gdn.rentalan.ui.register.email

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityRegisterBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.register.otp.RegisterOtpActivity
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class RegisterEmailActivity : BaseActivity(), RegisterEmailContract.View {

  companion object {
    private const val REGISTER = "register"
    fun newInstance(context: Context): Intent {
      val intent = Intent(context, RegisterEmailActivity::class.java)
      return intent
    }
  }

  @Inject
  lateinit var mPresenter: RegisterEmailContract.Presenter
  private lateinit var binding: ActivityRegisterBinding

  override fun getPresenter(): BaseContract.Presenter? {
    return mPresenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
    mPresenter.attachView(this)

    initView()
  }

  override fun getOtp(otp: String) {
    val intent = Intent(this@RegisterEmailActivity, RegisterOtpActivity::class.java)
    intent.putExtra("otp", otp)
    startActivity(intent)
  }

  override fun showToast() {
    showToast("Email telah terdaftarkan", Toast.LENGTH_LONG)
  }

  private fun initView() {
    binding.btRegister.setOnClickListener {
      mPresenter.registerEmail(binding.etUsername.text.toString())
      showProgress(true)
    }
    binding.login.setOnClickListener {
      Router.goToLogin(this)
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