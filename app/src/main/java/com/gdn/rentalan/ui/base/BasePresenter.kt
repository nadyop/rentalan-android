package com.gdn.rentalan.ui.base

import com.gdn.rentalan.util.RxUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseContract.Presenter {

  private var disposableHolder: CompositeDisposable? = null

  override fun attach() {
    initDisposableHolder()
  }

  override fun dettach() {
    if (RxUtils.isDisposableInitialized(this.disposableHolder)) {
      RxUtils.disposeComposite(this.disposableHolder)
    }
  }

  private fun initDisposableHolder() {
    if (!RxUtils.isDisposableInitialized(this.disposableHolder)) {
      this.disposableHolder = CompositeDisposable()
    }
  }

  fun addDisposable(disposable: Disposable) {
    initDisposableHolder()
    this.disposableHolder?.add(disposable)
  }
}