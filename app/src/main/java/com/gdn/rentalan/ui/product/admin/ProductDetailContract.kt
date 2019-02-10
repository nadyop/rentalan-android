package com.gdn.rentalan.ui.product.admin

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductDetailContract {

    interface View: BaseContract.View {
        fun setData(items: ProductDetailUiModel)
        fun goToProductList()
    }

    interface Presenter: BaseContract.Presenter {
        fun getData(productId: String)
        fun verification(productId: String, accept: String)
        fun attachView(view: View)
    }
}