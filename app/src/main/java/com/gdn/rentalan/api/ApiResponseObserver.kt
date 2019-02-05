package com.gdn.rentalan.api

import android.util.Log
import com.gdn.rentalan.ui.base.BaseContract
import io.reactivex.observers.DisposableObserver

abstract class ApiResponseObserver<T: RestCommonResponse>(private val view: BaseContract.View) :
    DisposableObserver<T>() {

  abstract fun onApiSuccess(response: T)
  abstract fun onApiError(response: T)

  override fun onNext(response: T) {
    with(response) {
      if (code == 200) {
        onApiSuccess(this)
      } else {
        Log.e(javaClass.simpleName, "error : $code")
        onApiError(this)
      }
    }
  }

  override fun onError(e: Throwable) {
    Log.e("API_CALL", e.message, e)
  }

  override fun onComplete() {
    // nothing to lose
  }
}