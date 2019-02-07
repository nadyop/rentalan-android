package com.gdn.rentalan.ui.product

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductDetailContract {

    interface View: BaseContract.View {
        fun setData(content: ProductDetailUiModel)
        fun goToProductList()
    }

    interface Presenter: BaseContract.Presenter {
        fun getData(id: String)
        fun verification(productId: String, accept: String)
        fun attachView(view: ProductDetailContract.View)
    }
}