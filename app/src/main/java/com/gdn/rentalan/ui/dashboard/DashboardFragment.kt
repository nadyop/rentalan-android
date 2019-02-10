package com.gdn.rentalan.ui.dashboard

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.FragmentDashboardBinding
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.ui.main.user.UserMainActivity
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : BaseFragment() , DashboardContract.View {

  companion object {
    private const val DASHBOARD = "dashboard"
    fun newInstance(context: Context): Intent {
      val intent = Intent(context, UserMainActivity::class.java)
      return intent
    }
  }

  @Inject
  lateinit var presenter: DashboardContract.Presenter
  private lateinit var binding: FragmentDashboardBinding
  private var listAdapter: DashboardAdapter? = null
//  private var provinceCode: String = ""
//  private var cityCode: String = ""
//  private var keyword: String = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidSupportInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

//    this.provinceCode  = arguments!!.getString("edttext")
//    this.cityCode  = arguments!!.getString("edttext")
//    this.keyword = arguments!!.getString("edttext")

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.attachView(this)
    initView()
  }

  private fun initView() {
    val layoutManager = GridLayoutManager(context, 2)
    binding.recyclerView.layoutManager = layoutManager

    if (listAdapter == null) {
      listAdapter = DashboardAdapter(ArrayList())
    }
    binding.recyclerView.adapter = listAdapter

    presenter.fetchData()

    //    if (cityCode != "" || provinceCode != "" || keyword != "") {
//      presenter.fetchDataSearch(provinceCode, cityCode, keyword)
//    } else {
//      presenter.fetchData()
//    }
  }

  override fun fetchDataSuccess(list: MutableList<ProductDetailUiModel>) {
    listAdapter?.addItems(list)
    recyclerView.adapter = listAdapter
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

  override fun showNoData() {
    binding.tvNoData.visibility = View.VISIBLE
  }

}