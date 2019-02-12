package com.gdn.rentalan.ui.product.admin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.FragmentProductBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductFragment : BaseFragment(), ProductContract.View {

    @Inject
    lateinit var presenter: ProductContract.Presenter
    private lateinit var binding: FragmentProductBinding
    private var listAdapter: ProductListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initView()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        if (listAdapter == null) {
            listAdapter = ProductListAdapter(ArrayList())
        }
        binding.recyclerView.adapter = listAdapter
        presenter.fetchData()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        binding.tvNoData.visibility = View.VISIBLE
    }

    override fun fetchDataSuccess(list: MutableList<ProductDetailUiModel>) {
        listAdapter?.addItems(list)
        recyclerView.adapter = listAdapter
    }

    override fun showNoData() {
        binding.tvNoData.visibility = View.VISIBLE
    }
}