package com.gdn.rentalan.ui.main

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val api: ApiInterface,
    private val loginRepository: LoginRepository) : BasePresenter(), MainContract.Presenter {

  private lateinit var view: MainContract.View
  private val roleUser = loginRepository.role

  override fun attachView(view: MainContract.View) {
    this.view = view
  }

//  override fun loadUserInfo() {
//    roleUser?.let { view.showMenu(it) }
//  }
}