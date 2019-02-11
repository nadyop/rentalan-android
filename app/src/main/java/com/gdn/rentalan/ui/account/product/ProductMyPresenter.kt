package com.gdn.rentalan.ui.account.product

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.ui.product.model.ProductMapper
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductMyPresenter @Inject constructor(private var api: ApiInterface, loginRepository: LoginRepository) :
        BasePresenter(), ProductMyContract.Presenter {

    private lateinit var view: ProductMyContract.View
    private val subscriptions = CompositeDisposable()
    private var userId = loginRepository.userId

    override fun fetchData() {
        view.showProgress(true)
        val subscribe = api.getAllProductByOwner(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestListResponse<Product> ->
                    view.showProgress(false)
                    Log.d("getProductByOwner", "sukses nihh")
                  list.let {
                    val listItems: MutableList<ProductDetailUiModel> = ArrayList()
                    list.data.forEach { contentElement ->
                      listItems.add(ProductMapper.mapToProductDetailUiModel(contentElement))
                    }
                    view.fetchDataSuccess(listItems)

                    if (list.data.isEmpty()) {
                      view.showNoData()
                    }
                  }
                }, { error ->
                    view.showProgress(false)
                    Log.d("getCategory", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attachView(view: ProductMyContract.View) {
        this.view = view
    }

}