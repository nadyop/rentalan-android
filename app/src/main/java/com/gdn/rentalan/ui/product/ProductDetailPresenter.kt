package com.gdn.rentalan.ui.product

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
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
        val subscribe = api.getProductDetail(productId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: Product ->
                    view.showProgress(false)
                    list?.let {
                        val productDetail = it.id?.let { id ->
                            it.name?.let { name ->
                                it.description?.let { desc ->
                                    it.categoryName?.let { categoryName ->
                                        it.productImages?.let { productImage ->
                                            ProductDetailUiModel(id, name, desc, it.pricePerDay, it.stock, it.downPayment, it.lateCharge, categoryName, productImage)
                                        }
                                    }
                                }
                            }
                        }
                        productDetail?.let { detail ->
                            view.setData(detail)
                        }
                    }

                    Log.d("AAAAZ", "sukses nihh")
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