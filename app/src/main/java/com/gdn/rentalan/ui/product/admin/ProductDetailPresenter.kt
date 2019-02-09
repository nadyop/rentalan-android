package com.gdn.rentalan.ui.product.admin

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
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
      view.showProgress(true)
      val subscribe = api.getProductDetail(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<Product> ->
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
                      Log.d("AAAAZ", "product detail success")
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "product detail failed + ${error.message} + ==== + ${error.cause}")
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
            Log.d("AAAAZ", "verif product success")
            view.goToProductList()
          }, { error ->
            view.showProgress(false)
            Log.d("AAAAZ", "verif product failed + ${error.message} + ==== + ${error.cause}")
            view.showErrorMessage(error.localizedMessage)
          })

      subscriptions.add(subscribe)
    }

    override fun attachView(view: ProductDetailContract.View) {
        this.view = view
    }
}