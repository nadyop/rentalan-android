package com.gdn.rentalan.ui.dashboard

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleGridBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
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

        tvProductName.text = name
        tvProductPriceDay.text = pricePerDay.toString()

        cardView.setOnClickListener {
          Router.gotoProductDetail(it.context, this)
        }
      }
    }
  }
}