package com.gdn.rentalan.ui.product

import android.view.View
import com.gdn.rentalan.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductDetailActivity : BaseActivity(), ProductDetailContract.View {

    @Inject
    lateinit var presenter: ProductDetailContract.Presenter

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}