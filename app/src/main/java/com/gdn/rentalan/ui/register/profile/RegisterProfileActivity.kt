package com.gdn.rentalan.ui.register.profile

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityRegisterProfileBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import okhttp3.Route
import javax.inject.Inject

class RegisterProfileActivity : BaseActivity(), RegisterProfileContract.View{

  @Inject
  lateinit var presenter: RegisterProfileContract.Presenter
  private lateinit var binding: ActivityRegisterProfileBinding

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_register_profile)
    presenter.attachView(this)

  }

  override fun onResume() {
    super.onResume()

    binding.btNext.setOnClickListener {
      registerProfile()
    }
  }

  private fun registerProfile() {
    val username: String = binding.etUsername.text.toString()
    val email: String = binding.etEmail.text.toString()
    val phone: String = binding.etPhone.text.toString()
    val password: String = binding.etPassword.text.toString()
    val surename: String = binding.etSurename.text.toString()

    presenter.registerProfile(username, email, phone, password, surename)
  }

  override fun validateUsernameEmail(code: Int) {
    when (code) {
      409 -> binding.tvUsernameError.visibility = View.VISIBLE
      419 -> binding.tvEmailError.visibility = View.VISIBLE
      200 -> {
        showToast(getString(R.string.register_success), Toast.LENGTH_LONG)
        Router.goToLogin(this)
      }
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