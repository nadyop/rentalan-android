package com.gdn.rentalan.ui.main.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import com.gdn.rentalan.R
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.product.admin.ProductFragment
import com.gdn.rentalan.ui.user.UserFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, HasSupportFragmentInjector {

    companion object {
        private const val MAIN = "main"
        val CATEGORY_FRAGMENT_INDEX = 1
        val USER_FRAGMENT_INDEX = 2
        val PRODUCT_FRAGMENT_INDEX = 3
        var paramMainMenu: Int = 0

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    @Inject
    internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var currentFragmentIndex: Int = 0
    private var tabIndex = 0

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        presenter.attach()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = CategoryFragment()
        addFragment(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.menu, menu)
      return true
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_category -> {
                inflateCategoryFragmentIntoMainActivity()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_product -> {
                inflateProductFragmentIntoMainActivity()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                inflateUserFragmentIntoMainActivity()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun inflateCategoryFragmentIntoMainActivity() {
        currentFragmentIndex = CATEGORY_FRAGMENT_INDEX
        val fragment = CategoryFragment()
        addFragment(fragment)
    }

    private fun inflateProductFragmentIntoMainActivity() {
      currentFragmentIndex = PRODUCT_FRAGMENT_INDEX
      val fragment = ProductFragment()
      addFragment(fragment)
    }

    private fun inflateUserFragmentIntoMainActivity() {
      currentFragmentIndex = USER_FRAGMENT_INDEX
      val fragment = UserFragment()
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