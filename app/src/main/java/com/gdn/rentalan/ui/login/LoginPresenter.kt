package com.gdn.rentalan.ui.login

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.request.LoginRequest
import com.gdn.rentalan.api.response.Login
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val api: ApiInterface,
    private val loginRepository: LoginRepository): BasePresenter(), LoginContract.Presenter {

  private lateinit var view: LoginContract.View
  private val subscriptions = CompositeDisposable()
  private var roleUser = ""

  override fun attachView(view: LoginContract.View) {
    this.view = view
  }

  override fun login(username: String, password: String) {
    view.showProgress(true)
    val subscribe = api.login(LoginRequest(username, password))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response: RestSingleResponse<Login> ->
          response.data?.let {
            val items = LoginUiModel (
                it.userId,
                it.role.orEmpty()
            )
            this.setSharedPrefs(items)
            this.roleUser = items.role.toString()
          }
          if (response.code == 200) {
            view.goToMainPage(this.roleUser)
          }
          view.validate(response.code)
          view.showProgress(false)
          Log.d("AAAAZ", "login success")
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "login failed + ${error.message} + ==== + ${error.cause}")
        })

    subscriptions.add(subscribe)
  }

  protected fun setSharedPrefs(loginUiModel: LoginUiModel) {
    loginRepository.userId = loginUiModel.userID
    loginRepository.role = loginUiModel.role
  }

  override fun loadUserInfo() {
    roleUser.let {
      view.goToMainPage(it)
    }
  }
}