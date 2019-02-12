package com.gdn.rentalan.ui.transaction.detail

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel

class TransactionDetailContract {
    interface View : BaseContract.View {
        fun setData(content: TransactionUiModel)
        fun setDataRenter(content: TransactionUiModel)
        fun goToTransactionList()
        fun showLateCharge(lateCharge: Int)
    }

    interface Presenter : BaseContract.Presenter {
        fun getData(id: String)
        fun getRenterDetail(renterId: String)
        fun rentAcceptByOwner(transactionId: String, isOwner: Boolean)
        fun attachView(view: View)
        fun returnProduct(transactionId: String)
    }
}
