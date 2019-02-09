package com.gdn.rentalan.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityLoginBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.main.MainActivity
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View , HasActivityInjector {

  companion object {
    private const val LOGIN = "login"
    fun newInstance(context: Context): Intent {
      val intent = Intent(context, LoginActivity::class.java)
      return intent
    }
  }

  @Inject
  lateinit var presenter: LoginContract.Presenter
  @Inject
  internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  private lateinit var binding: ActivityLoginBinding

  override fun activityInjector(): AndroidInjector<Activity> {
    return this.activityInjector
  }

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

    presenter.attachView(this)
    initView()
  }

  override fun validate(code: Int) {
    when (code) {
      417 -> binding.tvLoginFailed.visibility = View.VISIBLE
      404 -> binding.tvUserNotfound.visibility = View.VISIBLE
      200 -> goToMainPage()
    }
  }

  private fun initView() {
    binding.tvRegister.setOnClickListener {
      Router.goToRegister(this)
    }
    login()
  }

  override fun goToMainPage() {
    val intent = Intent(this@LoginActivity, MainActivity::class.java)
    startActivity(intent)
  }

  private fun login() {
    binding.btLogin.setOnClickListener {
      Router.goToMain(this)
      presenter.login(
          binding.etUsername.text.toString(),
          binding.etPassword.text.toString()
      )
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