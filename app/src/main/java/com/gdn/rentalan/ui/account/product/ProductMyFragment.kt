package com.gdn.rentalan.ui.account.product

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.FragmentMyProductBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Router
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProductMyFragment : BaseFragment(), ProductMyContract.View {

    @Inject
    lateinit var presenter: ProductMyContract.Presenter
    private lateinit var binding: FragmentMyProductBinding
    private var listAdapter: ProductMyAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        retainInstance = true
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
    }

    private fun initView() {

        binding.btSave.setOnClickListener { it ->
            activity?.let { Router.goToProductAdd(it) }
        }

        val layoutManager = LinearLayoutManager(context)
        binding.rvItems.layoutManager = layoutManager
        if (listAdapter == null) {
            listAdapter = ProductMyAdapter(ArrayList())
        }
        binding.rvItems.adapter = listAdapter
        presenter.fetchData()
    }

    override fun fetchDataSuccess(list: MutableList<ProductDetailUiModel>) {
        listAdapter?.addItems(list)
        binding.rvItems.adapter = listAdapter
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