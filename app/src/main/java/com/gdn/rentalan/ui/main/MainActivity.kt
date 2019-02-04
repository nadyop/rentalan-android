package com.gdn.rentalan.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.gdn.rentalan.R
import com.gdn.rentalan.di.component.DaggerActivityComponent
import com.gdn.rentalan.di.module.ActivityModule
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.product.ProductFragment
import com.gdn.rentalan.ui.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = CategoryFragment()
        addFragment(fragment)
        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_category -> {
                val fragment = CategoryFragment()
                addFragment(fragment)
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
}