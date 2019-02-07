package com.gdn.rentalan.ui.user

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ItemSimpleBinding
import com.gdn.rentalan.ui.base.BaseRecyclerViewListAdapter
import com.gdn.rentalan.ui.user.model.UserDetailUiModel
import com.gdn.rentalan.util.Router

class UserListAdapter(
    userDetailUiModels: MutableList<UserDetailUiModel>)
    : BaseRecyclerViewListAdapter<UserDetailUiModel, UserListAdapter.ViewHolder>(userDetailUiModels) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemUserDiscussionBinding = DataBindingUtil.bind<ItemSimpleBinding>(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_simple, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        holder.itemUserDiscussionBinding?.apply {
            with(user) {
                tvTitle.text = sureName
                tvDescription.text = city

                container.setOnClickListener {
                    Router.gotoUserDetail(it.context, this)
                }
            }
        }
    }
}