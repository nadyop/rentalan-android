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
                    Log.d("AAAAZ", "sukses nihh")
                    Log.d("AAAAZgetData", response.data.toString())
                    response.data?.let {
                        val items = UserDetailUiModel(
                            it.address,
                            it.gender,
                            it.city,
                            it.facePhotoPath,
                            it.birthDate,
                            it.nik,
                            it.phoneNumber,
                            it.province,
                            it.ktpPhotoPath,
                            it.id,
                            it.sureName,
                            it.email,
                            it.selfPhotoPath,
                            it.username,
                            it.status
                        )
                        view.setData(items)
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun verification(productId: String, accept: String) {
      val subscribe = api.verifProduct(productId, accept)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ list: RestCommonResponse ->
            view.showProgress(false)
            Log.d("AAAAZ", "sukses add nihh")
            view.goToUserList()
          }, { error ->
            view.showProgress(false)
            Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
            view.showErrorMessage(error.localizedMessage)
          })

      subscriptions.add(subscribe)
    }

    override fun attachView(view: UserDetailContract.View) {
        this.view = view
    }

}