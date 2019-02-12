package com.gdn.rentalan.ui.transaction.renter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.transaction.detail.TransactionDetailActivity
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.util.Router

class TransactionAdapter(
        transactionUiModels: MutableList<TransactionUiModel>)
    : BaseRecyclerViewListAdapter<TransactionUiModel, TransactionAdapter.ViewHolder>(transactionUiModels) {

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
                    Router.gotoProductTransactionDetailRenter(it.context, this)
                }
            }
        }
    }
}