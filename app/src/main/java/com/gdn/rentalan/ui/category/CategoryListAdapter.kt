package com.gdn.rentalan.ui.category

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gdn.rentalan.R
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

        holder.title?.text = category.name
        holder.body?.text = category.description

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return CategoryListAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val body = itemView.findViewById<TextView>(R.id.item_body)
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Category)
        fun itemDetail(postId: String)
    }
}