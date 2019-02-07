package com.gdn.rentalan.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

abstract class BaseRecyclerViewListAdapter<T, VH : RecyclerView.ViewHolder> :
        RecyclerView.Adapter<VH> {

    var context: Context? = null
    var items: MutableList<T> = mutableListOf()
    var layoutInflater: LayoutInflater? = null

    constructor(items: MutableList<T>) {
        initItems(items)
    }

    @Deprecated("will be removed!") constructor(context: Context, items: MutableList<T>) {
        this.context = context
        this.layoutInflater = LayoutInflater.from(context)
        initItems(items)
    }

    private fun initItems(items: MutableList<T>) {
        this.items = items
    }

    fun addItem(addItem: T) {
        items.add(addItem)
        notifyItemInserted(this.items.size - 1)
    }

    fun addItems(addItems: List<T>) {
        for (addItem in addItems) {
            this.addItem(addItem)
        }
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    fun changeItemsData(newItems: List<T>) {
        if (items.isNotEmpty()) {
            items.clear()
        }
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.items.size
    }
}
