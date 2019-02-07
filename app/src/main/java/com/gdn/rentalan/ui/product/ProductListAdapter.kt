package com.gdn.rentalan.ui.product

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.ItemSimpleBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Router

class ProductListAdapter(
        productDetailUiModels: MutableList<ProductDetailUiModel>)
    : BaseRecyclerViewListAdapter<ProductDetailUiModel, ProductListAdapter.ViewHolder>(productDetailUiModels) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemProductDiscussionBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_simple, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)

        holder.itemProductDiscussionBinding?.apply {
            with(product) {
                tvTitle.text = name
                tvDescription.text = description

                container.setOnClickListener {
                    Router.gotoProductDetail(it.context, this)
                }
            }
        }
    }
}