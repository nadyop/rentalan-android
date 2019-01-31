package com.gdn.rentalan.ui.main

import com.gdn.rentalan.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showCategoryFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
    }
}