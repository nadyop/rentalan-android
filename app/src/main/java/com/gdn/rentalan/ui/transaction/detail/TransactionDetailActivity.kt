package com.gdn.rentalan.ui.transaction.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityTransactionDetailBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.util.AlarmReceiver
import com.gdn.rentalan.util.Constants
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import java.text.SimpleDateFormat
import java.util.*
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
    private val TIME_PICKER_ONCE_TAG = "TimePickerOnce"

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

    private fun showNotification() {
        val date = detail?.endDate


    }

    private fun userAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val checkOwner = intent.extras.getString("data")
        val endDate = detail?.endDate
        val endDateConvert: Date = SimpleDateFormat("dd/MM/yyyy").parse(endDate)
        val alarmReceiver = AlarmReceiver()


        if (detail?.status == "waiting" && checkOwner == "isOwner") {
            binding.llButtonRent.visibility = View.VISIBLE
            binding.buttonRent.text = getString(R.string.product_confirm)
            binding.buttonRent.setOnClickListener {
                detail?.id?.let { it1 -> presenter.rentAcceptByOwner(it1, true) }
                binding.buttonRent.isEnabled = false
                binding.buttonRent.setBackgroundColor(resources.getColor(R.color.disabled_color))
                showToast("Konfirmasi peminjaman berhasil", Toast.LENGTH_SHORT)
            }
        } else if (detail?.status == "accept by owner" && checkOwner != "isOwner") {    // on progress session
            binding.llButtonRent.visibility = View.VISIBLE
            binding.buttonRent.visibility = View.GONE
            binding.buttonProgress.text = getString(R.string.product_rent)
            binding.buttonProgress.setOnClickListener {
                detail?.id?.let { it1 -> presenter.rentAcceptByOwner(it1, false) }

                //  SET ALARM
                endDate?.let { it1 ->
                    alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                            it1,"07:00","Test")
                }
                binding.buttonRent.isEnabled = false
                binding.buttonRent.setBackgroundColor(resources.getColor(R.color.disabled_color))
                showToast("Barang sedang dipinjam", Toast.LENGTH_SHORT)
            }
        } else if (detail?.status == "on progress" && checkOwner == "isOwner") {
            binding.llButtonRent.visibility = View.VISIBLE
            binding.buttonRent.text = getString(R.string.product_return)
            binding.buttonRent.isEnabled = true
            binding.buttonRent.setBackgroundColor(resources.getColor(R.color.colorAccent))
            binding.buttonRent.setOnClickListener {
                detail?.id?.let { it1 -> presenter.returnProduct(it1) }
            }
//            showSnackbarAction()
        }

    }

    override fun showLateCharge(lateCharge: Int) {
        showToast("Denda : " + lateCharge, Snackbar.LENGTH_LONG)
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
        with(binding) {
            Glide.with(ivProduct.context).load(Constants.URL_PRODUCT + content.productImage).placeholder(
                    R.drawable.no_image_available
            ).into(ivProduct)
            tvProductName.text = content.name
            tvProductPriceDay.text = Constants.formatRupiah.format(content.pricePerDay.toString().toInt()) + " /hari"
            tvUserName.text = content.ownerName
            tvPhone.text = content.ownerPhoneNumber
            tvCity.text = content.ownerCity
            tvCategory.text = content.categoryName
            tvDescription.text = content.description
            tvDp.text = Constants.formatRupiah.format(content.downPayment.toString().toInt())
            tvLateCharge.text = content.lateCharge.toString()
            etStartDate.text = content.startDate
            etEndDate.text = content.endDate
            tvStockValue.text = content.quantity.toString()
        }
    }

    override fun setDataRenter(content: TransactionUiModel) {
        with(binding) {
            tvRenter.text = content.renterName
            tvRenterPhone.text = content.renterPhone
            tvRenterCity.text = content.renterCity
        }
    }

    override fun goToTransactionList() {
        Router.goToMain(this)
        showSnackbar("Transaksi telah dikonfirmasi", Snackbar.LENGTH_LONG)
        super.onBackPressed()
    }
}