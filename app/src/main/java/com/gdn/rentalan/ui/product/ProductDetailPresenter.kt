package com.gdn.rentalan.ui.product

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor(private val api: ApiInterface):
        BasePresenter(), ProductDetailContract.Presenter {

  private lateinit var view: ProductDetailContract.View
  private val subscriptions = CompositeDisposable()

  override fun getData(productId: String) {
    val subscribe = api.getProductDetail(productId).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestListResponse<Product> ->
          view.showProgress(false)
          list.data?.let {
            val a = ProductDetailUiModel(it.)
          }

          Log.d("AAAAZ", "sukses nihh")
          list.let { view.setData(list) }
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })
  }

  override fun verification(id: String) {
    Log.d("AAAAZ", "api verif")
  }

  override fun attachView(view: ProductDetailContract.View) {
    this.view = view
  }

}