package com.gdn.rentalan.ui.base

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R

abstract class BaseFragment : Fragment(), BaseContract.View {

  override fun onAttach(context: Context?) {
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = super.onCreateView(inflater, container, savedInstanceState)

    val presenter: BaseContract.Presenter? = getPresenter()
    presenter?.attach()

    return view
  }

  override fun onDestroyView() {
    super.onDestroyView()

    val presenter: BaseContract.Presenter? = getPresenter()
    presenter?.dettach()
  }

  protected fun getPresenter(): BaseContract.Presenter? {
    return null
  }

  protected fun generateBaseSnackbar(message: String, duration: Int, backgroundId: Int): Snackbar {
    return Snackbar.make(activity!!.findViewById(R.id.container), message, duration)
  }

}