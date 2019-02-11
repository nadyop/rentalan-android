package com.gdn.rentalan.ui.product.renter

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.request.RentRequest
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductActivityCheckoutPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), ProductActivityCheckoutContract.Presenter {

    private lateinit var view: ProductActivityCheckoutContract.View
    private val subscriptions = CompositeDisposable()

    override fun getData(productId: String) {
        view.showProgress(true)
        val subscribe = api.getProductDetail(productId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<Product> ->
                    Log.d("AAAAZ", "sukses nihh")
                    Log.d("AAAAZgetData", response.data.toString())
                    response.data?.let {
                        val items = ProductDetailUiModel(
                                it.id.orEmpty(),
                                it.name.orEmpty(),
                                it.description.orEmpty(),
                                it.pricePerDay,
                                it.stock,
                                it.downPayment,
                                it.lateCharge,
                                it.categoryName.toString(),
                                it.productImage
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

    override fun rent(productId: String, startDate: String, endDate: String, qty: Int) {
        val subscribe = api.rentCheckout(productId, RentRequest(quantity = qty, startDate = startDate, endDate = endDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestCommonResponse ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "sukses add nihh")
                    view.goToDashboard()
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attachView(view: ProductActivityCheckoutContract.View) {
        this.view = view
    }

}