package com.gdn.rentalan.ui.product

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityProductCheckoutBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.view.*
import java.util.*
import javax.inject.Inject


class ProductActivityCheckout : BaseActivity(), ProductActivityCheckoutContract.View {

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
    private var detail: ProductDetailUiModel? = null
    private var actionButtonClickListener: View.OnClickListener? = null

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
        detail?.id?.let {
            presenter.getData(it)
            Log.d("AAAAZ", "ini tu ga null")
        }
        userAction()
    }

    private fun userAction() {
        Log.d("AAAAZ", "userAction")
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sendDataListener(View.OnClickListener {
            Log.d("AAAAZproductId = ", detail?.id.orEmpty())
            presenter.rent(
                    detail?.id.orEmpty(),
                    binding.etStartDate.text.toString(),
                    binding.etEndDate.text.toString(),
                    binding.etCount.text.toString().toInt()
            )
        })

        binding.etStartDate.setOnClickListener {
            val c = Calendar.getInstance()
            c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val mYear = c.get(Calendar.YEAR) // current year
            val mMonth = c.get(Calendar.MONTH) // current month
            val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

            val mDatePickerDialog = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // TODO Auto-generated method stub
                        binding.etStartDate.setText(String.format(
                                "%02d-%02d-%04d", dayOfMonth, monthOfYear + 1,
                                year))
                        c.set(Calendar.YEAR, year)
                        c.set(Calendar.MONTH, monthOfYear)
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
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
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // TODO Auto-generated method stub
                        binding.etEndDate.setText(String.format(
                                "%02d-%02d-%04d", dayOfMonth, monthOfYear + 1,
                                year))
                        c.set(Calendar.YEAR, year)
                        c.set(Calendar.MONTH, monthOfYear)
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }, mYear, mMonth, mDay)
            c.set(Calendar.YEAR, 1980)
            c.set(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
            mDatePickerDialog.show()
        }
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.btOne.setOnClickListener(listener)
    }

    override fun setData(content: ProductDetailUiModel) {
        with(binding) {
            tvProductName.text = content.name
            tvProductPriceDay.text = "Rp " + content.pricePerDay.toString()
            tvUserName.text = content.name
            tvPhone.text = content.name
            tvCity.text = content.name
            tvCategory.text = content.categoryName
            tvDescription.text = content.description
            tvStock.text = content.stock.toString()
            tvDp.text = "Rp " + content.downPayment.toString()
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
        Router.goToMain(this)
        super.onBackPressed()
    }
}