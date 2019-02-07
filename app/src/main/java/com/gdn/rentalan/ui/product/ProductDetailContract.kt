package com.gdn.rentalan.ui.product

import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

class ProductDetailContract {

    interface View: BaseContract.View {
        fun fetchDataSuccess(list: RestListResponse<Product>)
        fun setData(detail: ProductDetailUiModel)
    }

    interface Presenter: BaseContract.Presenter {
        fun getData(id: String)
        fun verification(id: String)
        fun attachView(view: ProductDetailContract.View)
    }
}