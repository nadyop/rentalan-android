package com.gdn.rentalan.ui.account.profile

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountPresenter @Inject constructor(private val api: ApiInterface,
                                           loginRepository: LoginRepository) : BasePresenter(), AccountContract.Presenter {

    @Inject
    lateinit var view: AccountContract.View
    private val subscriptions = CompositeDisposable()
    private var userId = loginRepository.userId

    override fun getAccount() {
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
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun goToEditUserId() {
        view.goToEditProfile(userId)
    }

    override fun goToEditUserIdVerify() {
        view.goToEditProfileVerify(userId)
    }

    override fun attachView(view: AccountContract.View) {
        this.view = view
    }
}