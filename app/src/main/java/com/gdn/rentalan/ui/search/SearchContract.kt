package com.gdn.rentalan.ui.search

import com.gdn.rentalan.ui.base.BaseContract

class SearchContract {

  interface View: BaseContract.View {
    fun goToDashboardPage()
  }

  interface Presenter: BaseContract.Presenter {
    fun search(provinceCode: String, cityCode: String, searchKey: String)
    fun attachView(view: View)
  }
}