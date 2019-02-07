package com.gdn.rentalan.ui.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_category.*

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    fun BaseActivity() {}

    protected open fun getPresenter(): BaseContract.Presenter? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        Log.d(javaClass.simpleName, "oncreate")
        val presenter = getPresenter()
        presenter?.attach()
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.simpleName, "onresume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.simpleName, "onstart")

    }

    override fun onPause() {
        super.onPause()

        Log.d(javaClass.simpleName, "onpause")
    }

    override fun onDestroy() {
        Log.d(javaClass.simpleName, "ondestroy")

        val presenter = getPresenter()
        presenter?.dettach()

        super.onDestroy()
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    protected fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(findViewById(R.id.container), message, duration)

        return snackbar.show()
    }
}