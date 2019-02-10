package com.gdn.rentalan.ui.main.user

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import javax.inject.Inject

class UserMainPresenter @Inject constructor(private val api: ApiInterface,
    private val loginRepository: LoginRepository) : BasePresenter(),
    UserMainContract.Presenter {

  private var view: UserMainContract.View? = null
  override fun attachView(view: UserMainContract.View) {
    this.view = view
  }
}