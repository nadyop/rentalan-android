package com.gdn.rentalan.ui.category

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Category
import com.gdn.rentalan.databinding.FragmentCategoryBinding
import com.gdn.rentalan.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class CategoryFragment : BaseFragment(), CategoryContract.View {

    @Inject
    lateinit var presenter: CategoryContract.Presenter
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
        Log.e("Error", error)
    }

    override fun fetchDataSuccess(list: RestListResponse<Category>) {
        val adapter = activity?.let {
            list.data?.toMutableList()?.let { categories ->
                CategoryListAdapter(it, categories, this)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    companion object {
        val TAG: String = "ListFragment"
    }


}