package com.gdn.rentalan.ui.account.profile

import com.gdn.rentalan.ui.base.BaseContract

class AccountContract {

  interface View: BaseContract.View {
    fun setData(items: AccountUiModel)
  }

  interface Presenter: BaseContract.Presenter {
    fun attachView(view: View)
    fun getAccount()
  }
}