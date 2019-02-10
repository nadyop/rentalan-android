package com.gdn.rentalan.ui.main.user

import android.support.v4.app.Fragment
import com.gdn.rentalan.ui.base.BaseContract

class UserMainContract {

  interface View: BaseContract.View {
    fun addFragment(fragment: Fragment)
  }

  interface Presenter: BaseContract.Presenter {
    fun attachView(view: View)
  }
}