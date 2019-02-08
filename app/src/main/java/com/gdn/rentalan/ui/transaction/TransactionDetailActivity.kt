package com.gdn.rentalan.ui.transaction

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityTransactionDetailBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class TransactionDetailActivity : BaseActivity(), TransactionDetailContract.View {

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
    lateinit var presenter: TransactionDetailContract.Presenter
    private lateinit var binding: ActivityTransactionDetailBinding
    private var detail: TransactionUiModel? = null
    private var actionButtonClickListener: View.OnClickListener? = null

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_detail)
        presenter.attachView(this)
        detail = intent.getParcelableExtra(TRANSACTION)
        Log.d("AAAAZ", detail.toString())
        detail?.id?.let {
            presenter.getData(it)
            Log.d("AAAAZ", "ini tu ga null")
        }

        userAction()
    }

    private fun userAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sendDataListener(View.OnClickListener {
            presenter.rentAcceptByOwner()
        })
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.buttonRent.setOnClickListener(listener)
    }

    override fun showProgress(show: Boolean) {
//        if (show) {
//            binding.container.visibility = View.GONE
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.container.visibility = View.VISIBLE
//            binding.progressBar.visibility = View.GONE
//        }
    }

    override fun showNoData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setData(content: TransactionUiModel) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToTransactionList() {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}