package com.gdn.rentalan.ui.login

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityLoginBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View , HasActivityInjector {

  @Inject
  lateinit var presenter: LoginContract.Presenter
  @Inject
  internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>
  private lateinit var binding: ActivityLoginBinding
  private var actionButtonClickListener: View.OnClickListener? = null

  override fun activityInjector(): AndroidInjector<Activity> {
    return this.activityInjector
  }

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail_admin)
    presenter.attachView(this)

    login()
  }

  private fun login() {
    loginDataListener(View.OnClickListener {
      presenter.loginData(
          binding.etUsername.text.toString(),
          binding.etPassword.text.toString()
      )
    })
  }

  private fun loginDataListener(listener: View.OnClickListener) {
    this.actionButtonClickListener = listener
    binding.btLogin.setOnClickListener(listener)
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.progressBar.visibility = View.GONE
    }
  }

  override fun goToMain() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}