package com.gdn.rentalan.ui.category

import com.gdn.rentalan.ui.base.BaseContract

class CategoryAddContract {

    interface View:  BaseContract.View {
        fun goToCategoryList()
    }

    interface Presenter: BaseContract.Presenter {
        fun sendData(categoryName: String, categoryDesc: String)
        fun fetchData()
        fun attachView(view: View)
    }
}