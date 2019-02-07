package com.gdn.rentalan.ui.product

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: MutableList<ProductDetailUiModel>)
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}