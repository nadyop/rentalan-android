package com.gdn.rentalan.ui.register.profile

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.request.RegisterRequest
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterProfilePresenter @Inject constructor(private val api: ApiInterface):
    BasePresenter(), RegisterProfileContract.Presenter {

  private lateinit var view: RegisterProfileContract.View
  private val subscriptions = CompositeDisposable()

  override fun registerProfile(username: String, email: String, phoneNumber: String, password: String, sureName: String) {
    view.showProgress(true)
    val subscribe = api.registerProfile(RegisterRequest(
        password, phoneNumber, sureName, email, username))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestCommonResponse ->
          view.showProgress(false)
          view.validateUsernameEmail(list.code)
          Log.d("AAAAZ", "sukses add nihh")
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }

  override fun attachView(view: RegisterProfileContract.View) {
    this.view = view
  }

}