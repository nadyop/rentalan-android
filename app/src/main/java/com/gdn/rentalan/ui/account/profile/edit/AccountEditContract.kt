package com.gdn.rentalan.ui.account.profile.edit

import com.gdn.rentalan.api.request.UserVerifyRequest
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseContract
import java.io.File

class AccountEditContract {

  interface View: BaseContract.View {
    fun setData(details: AccountUiModel)
    fun goToMainPage()
  }

  interface Presenter: BaseContract.Presenter {
    fun saveDetail(request: UserVerifyRequest, ktpFile: File, selfFile: File)
    fun getDetail()
    fun attachView(view: View)
  }
}