package com.gdn.rentalan.ui.user

import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.api.response.UserResponse
import com.gdn.rentalan.ui.base.BaseContract

class UserContract {

    interface View:  BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: UserResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun deleteItem(item: User)
    }
}