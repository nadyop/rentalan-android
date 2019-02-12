package com.gdn.rentalan.ui.transaction.detail

import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel

class TransactionDetailContract {
  interface View : BaseContract.View {
    fun setData(content: TransactionUiModel)
    fun goToTransactionList()
  }

  interface Presenter : BaseContract.Presenter {
    fun getData(id: String)
    fun rentAcceptByOwner()
    fun attachView(view: View)
  }
}
