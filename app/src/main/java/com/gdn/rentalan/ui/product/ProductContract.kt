package com.gdn.rentalan.ui.product

import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BaseContract

class ProductContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: RestListResponse<Product>)
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}