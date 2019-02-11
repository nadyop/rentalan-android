package com.gdn.rentalan.ui.account.product.update

import com.gdn.rentalan.api.request.ProductVerifyRequest
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.category.CategoryUiModel
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import java.io.File

class ProductMyUpdateContract {

  interface View: BaseContract.View {
    fun gotoUserMain()
    fun setData(details: ProductDetailUiModel)
    fun showCategories(categories: List<CategoryUiModel>)
  }

  interface Presenter: BaseContract.Presenter {
    fun updateProductByOwner(productId: String, request: ProductVerifyRequest, image: File)
    fun getDetail(productId: String)
    fun attachView(view: View)
    fun getCategory()
  }
}