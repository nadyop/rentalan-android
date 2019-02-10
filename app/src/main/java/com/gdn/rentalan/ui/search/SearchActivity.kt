package com.gdn.rentalan.ui.search

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivitySearchBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.dashboard.DashboardFragment
import com.gdn.rentalan.ui.main.admin.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity: BaseActivity(), SearchContract.View {

  companion object {
    private const val SEARCH = "search"
    fun newInstance(context: Context): Intent {
      val intent = Intent(context, SearchActivity::class.java)
      return intent
    }
  }

  @Inject
  lateinit var presenter: SearchContract.Presenter
  private lateinit var binding: ActivitySearchBinding

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    presenter.attachView(this)

    binding.btSearch.setOnClickListener {
      goToDashboardPage()
    }
  }

  override fun goToDashboardPage() {
    val province = binding.etProvince.text.toString()
    val city = binding.etCity.text.toString()
    val key = binding.etKey.text.toString()

//    passing data
    val bundle = Bundle()
    bundle.putString("province", province)
    bundle.putString("city", city)
    bundle.putString("key", key)
    val search = DashboardFragment()
    search.arguments = bundle

    val intent = Intent(this@SearchActivity, MainActivity::class.java)
    intent.putExtra("province", province)
    intent.putExtra("city", city)
    intent.putExtra("key", key)
    startActivity(intent)
  }

  override fun showProgress(show: Boolean) {
    // nothing implementation
  }
}
