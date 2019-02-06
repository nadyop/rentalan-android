package com.gdn.rentalan.util

import com.gdn.rentalan.ui.base.BaseContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Observable<T>.subscribeWithLoadingDialogAndRetry(
    view: BaseContract.View): Observable<T> = this.observeOn(
    AndroidSchedulers.mainThread()).doOnSubscribe {
  view.showProgress(true)
}.doOnTerminate { view.showProgress(false) }