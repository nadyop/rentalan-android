package com.gdn.rentalan.ui.account.product

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Router

class ProductMyAdapter(
    productDetailUiModels: MutableList<ProductDetailUiModel>)
    : BaseRecyclerViewListAdapter<ProductDetailUiModel, ProductMyAdapter.ViewHolder>(productDetailUiModels) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemProductListBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_simple, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)

        holder.itemProductListBinding?.apply {
            with(product) {
                tvTitle.text = name
                tvDescription.text = status

                container.setOnClickListener {
                    Router.gotoProductUpdate(it.context, this)
                }
            }
        }
    }
}