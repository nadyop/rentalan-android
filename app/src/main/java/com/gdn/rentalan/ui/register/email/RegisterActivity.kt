package com.gdn.rentalan.ui.register.email

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityRegisterBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.register.otp.RegisterOtpActivity
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View {

  companion object {
    private const val REGISTER = "register"
    fun newInstance(context: Context): Intent {
      val intent = Intent(context, RegisterActivity::class.java)
      return intent
    }
  }

  @Inject
  lateinit var presenter: RegisterContract.Presenter
  private lateinit var binding: ActivityRegisterBinding

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
    presenter.attachView(this)

    initView()
  }

  override fun getOtp(otp: String) {
    val intent = Intent(this@RegisterActivity, RegisterOtpActivity::class.java)
    intent.putExtra("otp", otp)
    startActivity(intent)
  }

  private fun initView() {
    binding.btRegister.setOnClickListener {
      presenter.registerEmail(binding.etUsername.text.toString())
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