package com.gdn.rentalan.ui.transaction

import android.content.Context
import android.content.Intent
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import javax.inject.Inject

class TransactionDetailActivity : BaseActivity(), TransactionContract.View {

    companion object {
        private const val TRANSACTION = "detail"
        fun newInstance(context: Context, detail: TransactionUiModel): Intent {
            val intent = Intent(context, TransactionDetailActivity::class.java)
            intent.putExtra(
                    TRANSACTION, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: TransactionContract.Presenter

    override fun showProgress(show: Boolean) {
//        if (show) {
//            binding.container.visibility = View.GONE
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.container.visibility = View.VISIBLE
//            binding.progressBar.visibility = View.GONE
//        }
    }

    override fun fetchDataSuccess(list: MutableList<TransactionUiModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}