package com.gdn.rentalan.ui.main.admin

import android.support.v4.app.Fragment
import com.gdn.rentalan.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun addFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter {
        fun attachView(view: View)
    }
}