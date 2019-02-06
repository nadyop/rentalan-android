package com.gdn.rentalan.ui.product

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.ItemSimpleBinding

class ProductListAdapter(private val context: Context, private val list: MutableList<Product>,
                         fragment: Fragment) : RecyclerView.Adapter<ProductListAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = list[position]

        holder.itemCategoryBinding.let {
            it?.tvTitle?.text = product.name
            it?.tvDescription?.text = product.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false)
        return ProductListAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCategoryBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

}