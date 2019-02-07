package com.gdn.rentalan.ui.product

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), ProductDetailContract.Presenter {

    private lateinit var view: ProductDetailContract.View
    private val subscriptions = CompositeDisposable()

    override fun getData(id: String) {
        val subscribe = api.getProductDetail(id).subscribeOn(Schedulers.io())
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
                                it.categoryName,
                                it.productImages
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

    override fun verification(id: String) {
        Log.d("AAAAZ", "api verif")
    }

    override fun attachView(view: ProductDetailContract.View) {
        this.view = view
    }

}