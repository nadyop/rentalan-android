package com.gdn.rentalan.ui.login

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.request.LoginRequest
import com.gdn.rentalan.api.response.Login
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val api: ApiInterface):
    BasePresenter(), LoginContract.Presenter {

  private lateinit var view: LoginContract.View
  private val subscriptions = CompositeDisposable()

  override fun attachView(view: LoginContract.View) {
    this.view = view
  }

  override fun loginData(username: String, password: String) {
    val subscribe = api.login(LoginRequest(username = username, password = password))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestListResponse<Login> ->
          view.showProgress(false)
          Log.d("AAAAZ", "sukses add nihh")
          view.goToMain()
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }
}