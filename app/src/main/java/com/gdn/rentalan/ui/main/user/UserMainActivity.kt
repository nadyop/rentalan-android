package com.gdn.rentalan.ui.main.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import com.gdn.rentalan.R
import com.gdn.rentalan.ui.account.AccountPagerFragment
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.dashboard.DashboardFragment
import com.gdn.rentalan.ui.main.admin.MainActivity
import com.gdn.rentalan.ui.transaction.TransactionPagerFragment
import com.gdn.rentalan.util.AlarmReceiver
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class UserMainActivity : BaseActivity(), UserMainContract.View, HasSupportFragmentInjector {

  companion object {
    private const val MAIN = "main"
    val DASHBOARD_FRAGMENT_INDEX = 1
    val TRANSACTION_FRAGMENT_INDEX = 2
    val ACCOUNT_FRAGMENT_INDEX = 3
    var paramMainMenu: Int = 0

    fun newInstance(context: Context): Intent {
      val intent = Intent(context, MainActivity::class.java)
      return intent
    }
  }

  @Inject
  internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var presenter: UserMainContract.Presenter

  private var currentFragmentIndex: Int = 0
  private var tabIndex = 0

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return supportFragmentInjector
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_user)

    AndroidInjection.inject(this)

    presenter.attach()
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    val fragment = DashboardFragment()
    addFragment(fragment)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)
    return true
  }

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_home -> {
        inflateDashboardFragmentIntoMainActivity()
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_transaction -> {
        inflateTransactionFragmentIntoMainActivity()
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_account -> {
        inflateAccountFragmentIntoMainActivity()
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  private fun inflateDashboardFragmentIntoMainActivity() {
    currentFragmentIndex = DASHBOARD_FRAGMENT_INDEX
    val fragment = DashboardFragment()
    addFragment(fragment)
  }

  private fun inflateTransactionFragmentIntoMainActivity() {
    currentFragmentIndex = TRANSACTION_FRAGMENT_INDEX
    val fragment = TransactionPagerFragment()
    addFragment(fragment)
  }

  private fun inflateAccountFragmentIntoMainActivity() {
    currentFragmentIndex = ACCOUNT_FRAGMENT_INDEX
    val fragment = AccountPagerFragment()
    addFragment(fragment)
  }

  override fun addFragment(fragment: Fragment) {
    val data = Bundle()
    data.putInt("DEFAULT_TAB", tabIndex)
    supportFragmentManager
        .beginTransaction()
        .disallowAddToBackStack()
        .replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
        .commit()
  }

  override fun onBackPressed() {
    finish()
  }

  override fun showProgress(show: Boolean) {
    Log.d(javaClass.simpleName, "showprogress")
  }
}