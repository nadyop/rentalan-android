package com.gdn.rentalan.ui.category

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.request.CategoryRequest
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryAddPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), CategoryAddContract.Presenter {

    private lateinit var view: CategoryAddContract.View
    private val subscriptions = CompositeDisposable()

    override fun fetchData() {
        Log.d("AAAAZ", "belom ada api nya")
    }

    override fun attachView(view: CategoryAddContract.View) {
        this.view = view
    }

  override fun sendData(categoryName: String, categoryDesc: String) {

        val subscribe = api.addCategory(CategoryRequest(categoryName, categoryDesc))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestCommonResponse ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "sukses add nihh")
                    view.goToCategoryList()
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }
}