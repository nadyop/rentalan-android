package com.gdn.rentalan.ui.register.email

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.request.RegisterEmailRequest
import com.gdn.rentalan.api.response.RegisterEmailResponse
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterPresenter @Inject constructor(private val api: ApiInterface) :
    BasePresenter(), RegisterContract.Presenter {

  private lateinit var view: RegisterContract.View
  private val subscriptions = CompositeDisposable()

  override fun attachView(view: RegisterContract.View) {
    this.view = view
  }

  override fun registerEmail(email: String) {
    val subscribe = api.registerEmail(RegisterEmailRequest(email))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RegisterEmailResponse ->
          view.showProgress(false)
          Log.d("AAAAZ", "sukses add nihh")
          view.getOtp(list.data.toString())
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }
}