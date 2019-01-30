package com.gdn.rentalan.ui.category

import android.content.Context
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemCategoryBinding
import com.gdn.rentalan.models.Category

class CategoryListAdapter(private val context: Context, private val list: MutableList<Category>,
                          fragment: Fragment) : RecyclerView.Adapter<CategoryListAdapter.ListViewHolder>() {

    private val listener: CategoryListAdapter.onItemClickListener

    init {
        this.listener = fragment as CategoryListAdapter.onItemClickListener
    }


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
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryListAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCategoryBinding = DataBindingUtil.bind<ItemCategoryBinding>(itemView)
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Category)
        fun itemDetail(postId: String)
    }
}