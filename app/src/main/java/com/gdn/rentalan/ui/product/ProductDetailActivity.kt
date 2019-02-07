package com.gdn.rentalan.ui.product

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.ActivityProductDetailAdminBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductDetailActivity : BaseActivity(), ProductDetailContract.View {

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
        detail?.id?.let {
            presenter.getData(it)
        }
        userAction()
    }

    private fun userAction(){
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

    override fun setData(detail: ProductDetailUiModel) {
        with(binding) {
            tvProductName.text = detail.name
            tvProductPriceDay.text = detail.pricePerDay.toString()
            tvUserName.text = detail.name
            tvPhone.text = detail.name
            tvCity.text = detail.name
            tvCategory.text = detail.categoryName
            tvDescription.text = detail.description
            tvDp.text = detail.downPayment.toString()
        }
    }

    override fun fetchDataSuccess(list: RestListResponse<Product>) {
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}