package com.gdn.rentalan.ui.product.admin

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

    override fun getData(productId: String) {
      view.showProgress(true)
      val subscribe = api.getProductDetail(productId).subscribeOn(Schedulers.io())
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
                                it.lateCharge, it.categoryName.toString(),
                                it.productImage, it.ownerCity.toString(), it.ownerName.toString(),
                            it.ownerPhoneNumber.toString()
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

    override fun verification(productId: String, accept: String) {
      val subscribe = api.verifProduct(productId, accept)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ list: RestCommonResponse ->
            view.showProgress(false)
          }, { error ->
            view.showProgress(false)
            view.showErrorMessage(error.localizedMessage)
          })

      subscriptions.add(subscribe)
    }

    override fun attachView(view: ProductDetailContract.View) {
        this.view = view
    }
}