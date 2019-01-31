package com.gdn.rentalan.ui.main

import android.support.v4.app.Fragment
import com.gdn.rentalan.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun addFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
    }
}