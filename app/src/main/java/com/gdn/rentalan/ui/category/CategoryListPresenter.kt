package com.gdn.rentalan.ui.category

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.ApiResponseObserver
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Category
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.subscribeWithLoadingDialogAndRetry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryListPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), CategoryContract.Presenter {

    private lateinit var view: CategoryContract.View
    private val subscriptions = CompositeDisposable()

    override fun fetchData() {
        view.showProgress(true)
        val subscribe = api.getCategoryList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestListResponse<Category> ->
                    view.showProgress(false)
                    Log.d("getCategory", "sukses nihh")
                    list.let {
                      view.fetchDataSuccess(it)
                    }
                    if (list.data.isEmpty()) {
                      view.showNoData()
                    }
                }, { error ->
                    view.showProgress(false)
                    Log.d("getCategory", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attachView(view: CategoryContract.View) {
        this.view = view
    }

    override fun attach() {
        super.attach()
        fetchData()
    }
}