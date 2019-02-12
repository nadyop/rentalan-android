package com.gdn.rentalan.ui.transaction.renter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.FragmentTransactionBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_transaction.*
import javax.inject.Inject

class TransactionFragment : BaseFragment(),
    TransactionContract.View {

    @Inject
    lateinit var presenter: TransactionContract.Presenter
    private lateinit var binding: FragmentTransactionBinding
    private var listAdapter: TransactionAdapter? = null
    private lateinit var transactionUiModel: TransactionUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
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
            listAdapter = TransactionAdapter(ArrayList())
        }
        binding.recyclerView.adapter = listAdapter
        presenter.fetchData()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun showErrorMessage(error: String) {
        binding.tvNoData.visibility = View.VISIBLE
    }

    override fun fetchDataSuccess(list: MutableList<TransactionUiModel>) {
        listAdapter?.addItems(list)
        recyclerView.adapter = listAdapter
    }

    override fun showNoData() {
        binding.tvNoData.visibility = View.VISIBLE
    }
}