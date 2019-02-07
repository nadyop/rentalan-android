package com.gdn.rentalan.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.Menu
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.product.ProductFragment
import com.gdn.rentalan.ui.user.UserFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, HasSupportFragmentInjector {

    @Inject
    internal lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val CATEGORY_FRAGMENT_INDEX = 1
    private val USER_FRAGMENT_INDEX = 2
    private val PRODUCT_FRAGMENT_INDEX = 3

    private var currentFragmentIndex = CATEGORY_FRAGMENT_INDEX
    private var tabIndex = 0
    private var mFragmentManager: FragmentManager? = null


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = CategoryFragment()
        addFragment(fragment)
        presenter.attach()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_category -> {
                inflateCategoryFragmentIntoMainActivity(tabIndex)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_product -> {
                val fragment = ProductFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                val fragment = UserFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun inflateCategoryFragmentIntoMainActivity(tabIndex: Int) {
        currentFragmentIndex = CATEGORY_FRAGMENT_INDEX
        val fragment = CategoryFragment()
        addFragment(fragment)

        val data = Bundle()
        data.putInt("DEFAULT_TAB", tabIndex)
        val xfragmentTransaction = mFragmentManager?.beginTransaction()
        xfragmentTransaction?.replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
        fragment.arguments = data
        xfragmentTransaction?.commit()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onBackPressed() {
        finish()
    }

    override fun showProgress(show: Boolean) {
        Log.d(javaClass.simpleName, "showprogress")
    }
}