package com.gdn.rentalan.ui.user

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.databinding.ItemSimpleBinding

class UserListAdapter(private val context: Context, private val list: MutableList<User>,
                      fragment: Fragment) : RecyclerView.Adapter<UserListAdapter.ListViewHolder>() {

    private val listener: UserListAdapter.onItemClickListener

    init {
        this.listener = fragment as UserListAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = list[position]

        holder.itemCategoryBinding.let {
            it?.tvTitle?.text = user.sureName
            it?.tvDescription?.text = user.city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_simple, parent, false)
        return UserListAdapter.ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCategoryBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: User)
        fun itemDetail(postId: String)
    }
}