package com.gdn.rentalan.ui.category

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.response.CategoryResponse
import com.gdn.rentalan.databinding.FragmentCategoryBinding
import com.gdn.rentalan.di.component.DaggerFragmentComponent
import com.gdn.rentalan.di.module.FragmentModule
import com.gdn.rentalan.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import javax.inject.Inject


class CategoryFragment : BaseFragment(), CategoryContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: CategoryContract.Presenter
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var bindingActivity: AddCategoryActivity
    private var categoryActivity: AddCategoryActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.refresh.setOnRefreshListener(this)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onRefresh() {
        view?.refresh.let {
            presenter.loadData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
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

    override fun loadDataSuccess(list: CategoryResponse) {
        val adapter = activity?.let {
            list.data?.toMutableList()?.let { categories ->
                CategoryListAdapter(it, categories)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun initView() {
        presenter.loadData()
        this.showAddCategory()
    }

    override fun showAddCategory() {
        val itemCategory = String

        binding.btAdd.setOnClickListener {
            val intent = Intent(context, AddCategoryActivity::class.java)
            context?.startActivity(intent)
        }
    }

    companion object {
        val TAG: String = "ListFragment"
    }
}