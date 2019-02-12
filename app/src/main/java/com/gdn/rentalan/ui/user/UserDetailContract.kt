package com.gdn.rentalan.ui.user

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.user.model.UserDetailUiModel

class UserDetailContract {

    interface View: BaseContract.View {
        fun setData(content: UserDetailUiModel)
        fun goToUserList()
    }

    interface Presenter: BaseContract.Presenter {
        fun getData(id: String)
        fun verification(userId: String)
        fun attachView(view: View)
    }
}