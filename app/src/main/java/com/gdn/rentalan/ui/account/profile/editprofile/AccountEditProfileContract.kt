package com.gdn.rentalan.ui.account.profile.editprofile

import com.gdn.rentalan.api.request.UserUpdateRequest
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseContract

class AccountEditProfileContract {

    interface View: BaseContract.View {
        fun setData(details: AccountUiModel)
        fun goToMainPage()
    }

    interface Presenter: BaseContract.Presenter {
        fun saveDetail(request: UserUpdateRequest)
        fun getDetail()
        fun attachView(view: View)
    }
}