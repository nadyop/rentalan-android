package com.gdn.rentalan.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_category.*

abstract class BaseActivity: AppCompatActivity(), BaseContract.View {

  fun BaseActivity(){}

  protected open fun getPresenter(): BaseContract.Presenter? {
    return null
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val presenter = getPresenter()
    presenter?.attach()
  }

  override fun onDestroy() {
    val presenter = getPresenter()
    presenter?.dettach()

    super.onDestroy()
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      progressBar.visibility = View.VISIBLE
    } else {
      progressBar.visibility = View.GONE
    }
  }

  override fun showErrorMessage(error: String) {
    Log.e("Error", error)
  }
}