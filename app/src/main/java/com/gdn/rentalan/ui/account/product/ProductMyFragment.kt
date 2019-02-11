package com.gdn.rentalan.ui.account.product

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.databinding.FragmentMyProductBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.util.Router
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProductMyFragment : BaseFragment(), ProductMyContract.View {

    @Inject
    lateinit var presenter: ProductMyContract.Presenter
    private lateinit var binding: FragmentMyProductBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        initView()
        binding.btSave.setOnClickListener { it ->
            activity?.let { Router.goToProductAdd(it) }
        }
    }

    private fun initView() {
        presenter.fetchData()
    }

    override fun fetchDataSuccess(list: RestListResponse<Product>) {
        val adapter = activity?.let {
            list.data?.toMutableList()?.let { products ->
                ProductMyAdapter(it, products, this)
            }
        }
        binding.rvItems.layoutManager = LinearLayoutManager(activity)
        binding.rvItems.adapter = adapter
    }

    override fun showNoData() {
        binding.tvNoData.visibility = View.VISIBLE
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}