package com.gdn.rentalan.ui.category

import com.gdn.rentalan.api.response.Category
import com.gdn.rentalan.api.response.CategoryResponse
import com.gdn.rentalan.ui.base.BaseContract

class CategoryContract {

    interface View:  BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: CategoryResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun deleteItem(item: Category)
    }
}