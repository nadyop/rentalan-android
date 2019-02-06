package com.gdn.rentalan.ui.product

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductListPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), ProductContract.Presenter {

    private lateinit var view: ProductContract.View
    private val subscriptions = CompositeDisposable()

    override fun fetchData() {
        val subscribe = api.getProductListWaiting().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestListResponse<Product> ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "sukses nihh")
                    list.let { view.fetchDataSuccess(it) }
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attachView(view: ProductContract.View) {
        this.view = view
    }

    override fun attach() {
        super.attach()
        fetchData()
    }
}