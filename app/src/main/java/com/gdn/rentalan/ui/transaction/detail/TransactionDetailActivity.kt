package com.gdn.rentalan.ui.transaction.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityTransactionDetailBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.util.Constants
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class TransactionDetailActivity : BaseActivity(),
    TransactionDetailContract.View {

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
        if (show) {
            binding.container.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.container.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun setData(content: TransactionUiModel) {
        Glide.with(this).load(Constants.URL_PRODUCT + content.productImage).fitCenter().into(binding.ivProduct)
        with(binding) {
            tvProductName.text = content.name
            tvProductPriceDay.text = Constants.formatRupiah.format(content.pricePerDay.toString().toInt()) + " /hari"
            tvUserName.text = content.ownerName
            tvPhone.text = content.ownerPhoneNumber
            tvCity.text = content.ownerCity
            tvCategory.text = content.categoryName
            tvDescription.text = content.description
            tvStock.text = content.stock.toString()
            tvDp.text = Constants.formatRupiah.format(content.downPayment.toString().toInt())
            tvLateCharge.text = content.lateCharge.toString()
            tvStock.text = content.stock.toString()
            etStartDate.text = content.startDate
            etEndDate.text = content.endDate
            tvStockValue.text = content.quantity.toString()
            tvRenter.text = content.renterName
            tvRenterPhone.text = content.renterPhone
            tvCity.text = content.renterCity
        }
    }

    override fun goToTransactionList() {
        Router.goToMain(this)
        showSnackbar("Transaksi telah dikonfirmasi", Snackbar.LENGTH_LONG)
        super.onBackPressed()
    }
}