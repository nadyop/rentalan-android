package com.gdn.rentalan.ui.user

import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BaseContract

class UserContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: RestListResponse<User>)
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}