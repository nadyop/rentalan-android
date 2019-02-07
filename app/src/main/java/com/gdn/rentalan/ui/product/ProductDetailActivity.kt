package com.gdn.rentalan.ui.product

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.ActivityProductDetailAdminBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_product_detail_admin.*
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductDetailActivity : BaseActivity(), ProductDetailContract.View {

    companion object {
        private const val DETAIL = "detail"
        fun newInstance(context: Context, detail: ProductDetailUiModel): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(DETAIL, detail) //from @Parcelize
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
        detail = intent.getParcelableExtra(DETAIL)
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

        sendDataListener(View.OnClickListener {
            presenter.verification(
                detail?.id.orEmpty()
            )
        })
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.tvActivation.setOnClickListener(listener)
    }

    override fun setData(content: ProductDetailUiModel) {
        with(binding) {
            tvProductName.text = content.name
            tvProductPriceDay.text = content.pricePerDay.toString()
            tvUserName.text = content.name
            tvPhone.text = content.name
            tvCity.text = content.name
            tvCategory.text = content.categoryName
            tvDescription.text = content.description
            tvDp.text = content.downPayment.toString()
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}