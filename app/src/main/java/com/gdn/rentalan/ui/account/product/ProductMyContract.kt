package com.gdn.rentalan.ui.account.product

import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.base.BaseContract

class ProductMyContract {

    interface View: BaseContract.View {
        fun fetchDataSuccess(list: RestListResponse<Product>)
        fun showNoData()
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}