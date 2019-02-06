package com.gdn.rentalan.ui.category

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleBinding
import com.gdn.rentalan.api.response.Category

class CategoryListAdapter(private val context: Context, private val list: MutableList<Category>,
                          fragment: Fragment) : RecyclerView.Adapter<CategoryListAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val category = list[position]

        holder.itemCategoryBinding.let {
            it?.tvTitle?.text = category.name
            it?.tvDescription?.text = category.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false)
        return CategoryListAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCategoryBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

}