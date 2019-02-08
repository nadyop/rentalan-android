package com.gdn.rentalan.ui.product.admin

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: MutableList<ProductDetailUiModel>)
        fun showNoData()
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}