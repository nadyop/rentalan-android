package com.gdn.rentalan.ui.search

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val api: ApiInterface) : BasePresenter(),
    SearchContract.Presenter {

  private lateinit var view: SearchContract.View
  private val subscriptions = CompositeDisposable()

  override fun search(provinceCode: String, cityCode: String, searchKey: String) {
//    nothing implementation
  }

  override fun attachView(view: SearchContract.View) {
    this.view = view
  }
}