package com.gdn.rentalan.ui.dashboard

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.ui.product.model.ProductMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashboardPresenter @Inject constructor(private val api: ApiInterface) :
    BasePresenter(), DashboardContract.Presenter {

  private lateinit var view: DashboardContract.View
  private val subscriptions = CompositeDisposable()

  override fun fetchData() {
    val subscribe = api.getProductListActive().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestListResponse<Product> ->
          view.showProgress(false)
          Log.d("AAAAZ", "sukses nihh")
          val listItems: MutableList<ProductDetailUiModel> = ArrayList()
          list.data.forEach { contentElement ->
            listItems.add(ProductMapper.mapToProductDetailUiModel(contentElement))
          }
          view.fetchDataSuccess(listItems)
          if (list.data.isEmpty()) {
            view.showNoData()
          }

        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }

  override fun attachView(view: DashboardContract.View) {
    this.view = view
  }
}