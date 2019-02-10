package com.gdn.rentalan.ui.product.renter

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityProductCheckoutBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Constants
import com.gdn.rentalan.util.Constants.Companion.URL_PRODUCT
import com.gdn.rentalan.util.Constants.Companion.formatRupiah
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class ProductActivityCheckout : BaseActivity(),
    ProductActivityCheckoutContract.View {

    companion object {
        private const val CHECKOUT = "detail"
        fun newInstance(context: Context, detail: ProductDetailUiModel): Intent {
            val intent = Intent(context, ProductActivityCheckout::class.java)
            intent.putExtra(
                CHECKOUT, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: ProductActivityCheckoutContract.Presenter
    private lateinit var binding: ActivityProductCheckoutBinding
    private lateinit var detail: ProductDetailUiModel
    private var actionButtonClickListener: View.OnClickListener? = null
    private var endDate: Long = 0
    private var startDate: Long = 0

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_checkout)
        presenter.attachView(this)
        detail = intent.getParcelableExtra(
            CHECKOUT)
        Log.d("AAAAZ", detail.toString())
        detail.id.let {
          presenter.getData(it)
        }
        userAction()
    }

    private fun userAction() {
        setData(detail)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sendDataListener(View.OnClickListener {
            presenter.rent(
                    detail.id,
                    binding.etStartDate.text.toString(),
                    binding.etEndDate.text.toString(),
                    binding.etCount.text.toString().toInt()
            )
        })

        binding.etStartDate.setOnClickListener {
            val c = Calendar.getInstance()
            c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val mDatePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        binding.etStartDate.setText(String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear + 1,year))
                        c.set(Calendar.YEAR, year)
                        c.set(Calendar.MONTH, monthOfYear)
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        startDate = c.timeInMillis
                    }, mYear, mMonth, mDay)
            c.set(Calendar.YEAR, 1980)
            c.set(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
            mDatePickerDialog.show()
        }

        binding.etEndDate.setOnClickListener {
            val c = Calendar.getInstance()
            c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val mYear = c.get(Calendar.YEAR) // current year
            val mMonth = c.get(Calendar.MONTH) // current month
            val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

            val mDatePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        binding.etEndDate.setText(String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear + 1, year))
                        c.set(Calendar.YEAR, year)
                        c.set(Calendar.MONTH, monthOfYear)
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        endDate = c.timeInMillis
                    }, mYear, mMonth, mDay)
            c.set(Calendar.YEAR, 1980)
            c.set(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
            mDatePickerDialog.show()
        }

      binding.tvTotal.setOnClickListener {
          if (binding.etCount.text.toString() != "" || !endDate.equals(0) || !startDate.equals(0) ||
              binding.etEndDate.text.toString() != "" || binding.etStartDate.text.toString() != ""){

              val stock: Int = binding.etCount.text.toString().toInt()
              val pricePerDay = detail.pricePerDay
              val day = endDate - startDate   //millis
              val days = Math.ceil(day / (1000.0 * 60.0 * 60.0 * 24.0)).toInt()
              val checkPrice = days * stock * pricePerDay
              binding.tvTotal.text = formatRupiah.format(checkPrice)
          } else {
              showToast("Please fill in the blank", Toast.LENGTH_LONG)
          }
      }

      binding.buttonRent.setOnClickListener {
          presenter.rent(detail.id,
              binding.etStartDate.text.toString(),
              binding.etEndDate.text.toString(),
              binding.etCount.text.toString().toInt()
          )
          Log.d("AAAAK", detail.toString())
      }
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.btOne.setOnClickListener(listener)
    }

    override fun setData(content: ProductDetailUiModel) {
        Glide.with(this).load(URL_PRODUCT + content.productImage).fitCenter().into(binding.ivProduct)
        with(binding) {
            tvProductName.text = content.name.capitalize()
            tvProductPriceDay.text = formatRupiah.format(content.pricePerDay.toString().toInt()) + "/hari"
            tvUserName.text = content.ownerName
            tvPhone.text = content.ownerPhone
            tvCity.text = content.ownerCity
            tvCategory.text = content.categoryName
            tvDescription.text = content.description
            tvStock.text = content.stock.toString()
            tvDp.text = formatRupiah.format(content.downPayment.toString().toInt())
        }
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

    override fun goToDashboard() {
        Router.goToUserMain(this)
        showToast("Silahkan tunggu konfirmasi dari pemilik", Toast.LENGTH_LONG)
        super.onBackPressed()
    }
}