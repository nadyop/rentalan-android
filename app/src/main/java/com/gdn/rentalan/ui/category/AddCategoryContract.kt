package com.gdn.rentalan.ui.category

import com.gdn.rentalan.ui.base.BaseContract

class AddCategoryContract {

    interface View: BaseContract.View {
        fun showCategory(category: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun addCategory(name: String, desc: String)
    }
}