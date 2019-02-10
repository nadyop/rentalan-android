package com.gdn.rentalan.ui.account.profile.edit

import com.gdn.rentalan.api.request.UserRequest
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseContract

class AccountEditContract {

  interface View: BaseContract.View {
    fun setData(details: AccountUiModel)
    fun goToMainPage()
  }

  interface Presenter: BaseContract.Presenter {
    fun saveDetail(details: UserRequest)
    fun getDetail()
    fun attachView(view: View)
  }
}