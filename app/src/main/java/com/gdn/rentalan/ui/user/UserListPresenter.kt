package com.gdn.rentalan.ui.user

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.user.model.UserDetailUiModel
import com.gdn.rentalan.ui.user.model.UserMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), UserContract.Presenter {

    private lateinit var view: UserContract.View
    private val subscriptions = CompositeDisposable()

  override fun fetchData() {
    val subscribe = api.getUserList().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestListResponse<User> ->
          view.showProgress(false)
          Log.d("AAAAZ", "sukses nihh")
          list.data.let {
            if(it.isNotEmpty()) {
              var listItems: MutableList<UserDetailUiModel> = ArrayList()
              it.forEach { contentElement ->
                listItems.add(UserMapper.mapToUserDetailUiModel(contentElement))
              }
              view.fetchDataSuccess(listItems)
            }

            if (it.isEmpty()) {
              view.showNoData()
            }

          }
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }

    override fun attachView(view: UserContract.View) {
        this.view = view
    }

    override fun attach() {
        super.attach()
        fetchData()
    }
}