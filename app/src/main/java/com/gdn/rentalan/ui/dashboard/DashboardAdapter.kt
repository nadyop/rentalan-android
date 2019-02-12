package com.gdn.rentalan.ui.dashboard

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleGridBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Constants.Companion.URL_PRODUCT
import com.gdn.rentalan.util.Constants.Companion.formatRupiah
import com.gdn.rentalan.util.Router

class DashboardAdapter(
    productDetailUiModels: MutableList<ProductDetailUiModel>)
  : BaseRecyclerViewListAdapter<ProductDetailUiModel, DashboardAdapter.ViewHolder>(productDetailUiModels) {

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemGridBinding = DataBindingUtil.bind<ItemSimpleGridBinding>(itemView)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(
        R.layout.item_simple_grid, parent, false
    )
    return ViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val product = getItem(position)

    holder.itemGridBinding?.apply {
      with(product) {
        Glide.with(ivProduct.context).load(URL_PRODUCT + productImage).placeholder(
            R.drawable.no_image_available
        ).into(ivProduct)
        tvProductName.text = name.capitalize()
        tvProductPriceDay.text = formatRupiah.format(pricePerDay.toString().toInt()) + "/hari"

        cardView.setOnClickListener {
          Router.gotoProductCheckoutDetail(it.context, this)
        }
      }
    }
  }
}