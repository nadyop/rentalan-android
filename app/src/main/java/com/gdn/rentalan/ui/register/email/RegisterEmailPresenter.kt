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

class RegisterEmailPresenter @Inject constructor(private val api: ApiInterface) :
    BasePresenter(), RegisterEmailContract.Presenter {

  private lateinit var mView: RegisterEmailContract.View
  private val subscriptions = CompositeDisposable()

  override fun attachView(view: RegisterEmailContract.View) {
    this.mView = view
  }

  override fun registerEmail(email: String) {
    val subscribe = api.registerEmail(RegisterEmailRequest(email))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RegisterEmailResponse ->
          mView.showProgress(false)
          mView.getOtp(list.data.toString())
         if (list.code == 417){
           mView.showToast()
         }
        }, { error ->
          mView.showProgress(false)
          mView.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }
}