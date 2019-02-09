package com.gdn.rentalan.ui.main

import android.support.v4.app.Fragment
import com.gdn.rentalan.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun addFragment(fragment: Fragment)
//        fun showMenu(role: String)
    }

    interface Presenter: BaseContract.Presenter {
        fun attachView(view: View)
//        fun loadUserInfo()
    }
}