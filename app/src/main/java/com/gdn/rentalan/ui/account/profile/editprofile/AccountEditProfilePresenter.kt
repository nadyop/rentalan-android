package com.gdn.rentalan.ui.account.profile.editprofile

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.request.UserUpdateRequest
import com.gdn.rentalan.api.request.UserVerifyRequest
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountEditProfilePresenter @Inject constructor(private var api: ApiInterface, loginRepository: LoginRepository) : BasePresenter(), AccountEditProfileContract.Presenter {

    private lateinit var view: AccountEditProfileContract.View
    private var subscriptions = CompositeDisposable()
    private var userId = loginRepository.userId

    override fun saveDetail(request: UserUpdateRequest) {
        val subscribe = api.updateUser(userId, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestCommonResponse ->
                    Log.d("AAAAZZ", "update user success")
                    view.showProgress(false)
                    view.goToMainPage()
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun getDetail() {
        view.showProgress(true)
        val subscribe = api.getUserDetail(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<User> ->
                    response.data?.let {
                        val items = AccountUiModel(
                                it.id.orEmpty(),
                                it.username.orEmpty(),
                                it.email.orEmpty(),
                                it.phoneNumber.orEmpty(),
                                it.sureName.orEmpty(),
                                it.nik.orEmpty(),
                                it.gender.orEmpty(),
                                it.birthDate.orEmpty(),
                                it.address.orEmpty(),
                                it.city.orEmpty(),
                                it.province.orEmpty(),
                                it.ktpPhotoPath.orEmpty(),
                                it.selfPhotoPath.orEmpty(),
                                it.status.orEmpty()
                        )
                        view.setData(items)
                        Log.d("AAAAZ", items.toString())
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun attachView(view: AccountEditProfileContract.View) {
        this.view = view
    }

}