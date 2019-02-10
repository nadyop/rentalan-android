package com.gdn.rentalan.ui.product.admin

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityProductDetailAdminBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Constants.Companion.URL_PRODUCT
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class ProductDetailActivity : BaseActivity(),
    ProductDetailContract.View {

    companion object {
        private const val DETAIL = "detail"
        fun newInstance(context: Context, detail: ProductDetailUiModel): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(
                DETAIL, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: ProductDetailContract.Presenter
    private lateinit var binding: ActivityProductDetailAdminBinding
    private var detail: ProductDetailUiModel? = null
    private var actionButtonClickListener: View.OnClickListener? = null

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail_admin)
        presenter.attachView(this)
        detail = intent.getParcelableExtra(
            DETAIL)
        Log.d("AAAAZ", detail.toString())
        detail?.id?.let {
            presenter.getData(it)
            Log.d("AAAAZ", "ini tu ga null")
        }
        userAction()
    }

    private fun userAction(){
        Log.d("AAAAZ", "userAction")
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.buttonRight.setOnClickListener {
            presenter.verification(
                detail?.id.orEmpty(), true.toString()
            )
            goToProductList()
        }

        binding.buttonLeft.setOnClickListener {
            presenter.verification(
                detail?.id.orEmpty(), false.toString()
            )
            goToProductList()
        }
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.buttonRight.setOnClickListener(listener)
    }

    override fun setData(content: ProductDetailUiModel) {
        Glide.with(this).load(URL_PRODUCT + content.productImage).fitCenter().into(binding.ivProduct)
        with(binding) {
            tvProductName.text = content.name
            tvProductPriceDay.text = "Rp " + content.pricePerDay.toString() + " /hari"
            tvUserName.text = content.ownerName
            tvPhone.text = content.ownerPhone
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

    override fun goToProductList() {
        Router.goToMain(this)
        showSnackbar("Product activated", Snackbar.LENGTH_LONG)
        super.onBackPressed()
    }
}