package com.gdn.rentalan.ui.user

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.user.model.UserDetailUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), UserDetailContract.Presenter {

  private lateinit var view: UserDetailContract.View
    private val subscriptions = CompositeDisposable()

    override fun getData(userId: String) {
      view.showProgress(true)
      val subscribe = api.getUserDetail(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<User> ->
                    response.data?.let {
                        val items = UserDetailUiModel(
                            address = it.address,
                            gender = it.gender,
                            city = it.city,
                            birthDate = it.birthDate,
                            nik = it.nik,
                            phoneNumber = it.phoneNumber,
                            province = it.province,
                            ktpPhotoPath = it.ktpPhotoPath,
                            id = it.id,
                            sureName = it.sureName,
                            email = it.email,
                            selfPhotoPath = it.selfPhotoPath,
                            username = it.username,
                            status = it.status
                        )
                        view.setData(items)
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun verification(userId: String) {
      val subscribe = api.verifUser(userId)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ list: RestCommonResponse ->
            view.showProgress(false)
          }, { error ->
            view.showProgress(false)
            view.showErrorMessage(error.localizedMessage)
          })

      subscriptions.add(subscribe)
    }

    override fun attachView(view: UserDetailContract.View) {
        this.view = view
    }

}