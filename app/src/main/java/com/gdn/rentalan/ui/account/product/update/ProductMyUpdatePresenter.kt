package com.gdn.rentalan.ui.account.product.update

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.request.ProductVerifyRequest
import com.gdn.rentalan.api.response.Category
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.category.CategoryMapper
import com.gdn.rentalan.ui.category.CategoryUiModel
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.LoginRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class ProductMyUpdatePresenter @Inject constructor(private val api: ApiInterface, loginRepository: LoginRepository) :
    BasePresenter(), ProductMyUpdateContract.Presenter {

  private lateinit var view: ProductMyUpdateContract.View
  private val subscriptions = CompositeDisposable()
  private val gson = Gson()
  private var userId = loginRepository.userId

  override fun attachView(view: ProductMyUpdateContract.View) {
    this.view = view
  }

  override fun updateProductByOwner(productId: String, request: ProductVerifyRequest, image: File) {

    val productRequestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(request))
    val productRequestBodyPart = MultipartBody.Part.createFormData("request", "request.json", productRequestBody)

    val imageRequestBody = RequestBody.create(MediaType.parse("application/json"), image)
    val imageRequestBodyPart = MultipartBody.Part.createFormData("image", image.name, imageRequestBody)

    val subscribe = api.updateProductByOwner(productId, userId, imageRequestBodyPart, productRequestBodyPart)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestCommonResponse ->
          view.showProgress(false)
          Log.d("AAAAZ", "sukses add nihh")
          view.gotoUserMain()
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }

  override fun getDetail(productId: String) {
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
                it.lateCharge,
                it.categoryName.toString(),
                it.productImage,
                it.ownerCity.toString(),
                it.ownerName.toString(),
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

  override fun getCategory() {
    val subscribe = api.getCategoryList().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list: RestListResponse<Category> ->
          val listItems: MutableList<CategoryUiModel> = ArrayList()
          list.data.forEach { contentElement ->
            listItems.add(CategoryMapper.mapToCategoryUiModel(contentElement))
          }
          view.showCategories(listItems)
        }, { error ->
          view.showProgress(false)
          Log.d("getCategory", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })

    subscriptions.add(subscribe)
  }
}