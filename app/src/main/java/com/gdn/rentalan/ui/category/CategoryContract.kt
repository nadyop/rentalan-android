package com.gdn.rentalan.ui.category

import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Category
import com.gdn.rentalan.ui.base.BaseContract

class CategoryContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: RestListResponse<Category>)
        fun showNoData()
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}