package com.gdn.rentalan.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface RxUtils {
  companion object {
    fun dispose(disposable: Disposable?) {
      if (disposable != null && !disposable.isDisposed) {
        disposable.dispose()
      }
    }

    fun disposeComposite(compositeDisposable: CompositeDisposable?) {
      if (compositeDisposable != null && !compositeDisposable.isDisposed) {
        compositeDisposable.clear()
        compositeDisposable.dispose()
      }
    }

    fun isDisposableInitialized(disposable: Disposable?): Boolean {
      return disposable != null && !disposable.isDisposed
    }
  }
}
