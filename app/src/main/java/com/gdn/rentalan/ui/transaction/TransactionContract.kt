package com.gdn.rentalan.ui.transaction

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel

class TransactionContract {

    interface View:  BaseContract.View {
        fun fetchDataSuccess(list: MutableList<TransactionUiModel>)
        fun showNoData()
    }

    interface Presenter: BaseContract.Presenter {
        fun fetchData()
        fun attachView(view: View)
    }
}