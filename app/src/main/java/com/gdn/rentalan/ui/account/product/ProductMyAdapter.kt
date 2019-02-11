package com.gdn.rentalan.ui.account.product

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.ItemSimpleListBinding

class ProductMyAdapter(private val context: Context, private val list: MutableList<Product>,
                       fragment: Fragment) : RecyclerView.Adapter<ProductMyAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val category = list[position]

        holder.itemCategoryBinding.let {
            it?.tvTitle?.text = category.name
            it?.tvDescription?.text = category.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_simple_list, parent, false)
        return ProductMyAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCategoryBinding = DataBindingUtil.bind<ItemSimpleListBinding>(itemView)
    }
}