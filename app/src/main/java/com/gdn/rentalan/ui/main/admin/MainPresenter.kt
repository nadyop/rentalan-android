package com.gdn.rentalan.ui.main.admin

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(private val api: ApiInterface,
    private val loginRepository: LoginRepository) : BasePresenter(),
    MainContract.Presenter {

  private var view: MainContract.View? = null

  override fun attachView(view: MainContract.View) {
    this.view = view
  }
}