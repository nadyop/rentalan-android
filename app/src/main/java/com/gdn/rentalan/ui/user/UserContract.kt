package com.gdn.rentalan.ui.user

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.user.model.UserDetailUiModel

class UserContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: MutableList<UserDetailUiModel>)
        fun showNoData()
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}