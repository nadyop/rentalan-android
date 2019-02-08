package com.gdn.rentalan.ui.product.renter

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductActivityCheckoutContract {

    interface View : BaseContract.View {
        fun setData(content: ProductDetailUiModel)
        fun goToDashboard()
    }

    interface Presenter : BaseContract.Presenter {
        fun getData(id: String)
        fun rent(productId: String, startDate: String, endDate: String, qty: Int)
        fun attachView(view: View)
    }
}