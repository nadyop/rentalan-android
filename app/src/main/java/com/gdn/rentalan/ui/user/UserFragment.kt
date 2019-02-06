package com.gdn.rentalan.ui.user

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.databinding.FragmentCategoryBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.util.Router
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

class UserFragment : BaseFragment(), UserContract.View {

    @Inject
    lateinit var presenter: UserContract.Presenter
    private lateinit var binding: FragmentCategoryBinding
    private var refreshList: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        refreshList = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initView()
        binding.btAdd.setOnClickListener {
            activity?.let { Router.goToCategoryAdd(it) }
        }
    }


    private fun initView() {
        presenter.fetchData()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        binding.tvNoData.visibility = View.VISIBLE
    }

    override fun fetchDataSuccess(list: RestListResponse<User>) {
        val adapter = activity?.let {
            list.data?.toMutableList()?.let { categories ->
                UserListAdapter(it, categories, this)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    companion object {
        val TAG: String = "CategoryFragment"
    }

}