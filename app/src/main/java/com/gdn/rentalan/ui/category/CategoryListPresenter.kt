package com.gdn.rentalan.ui.category

import com.gdn.rentalan.api.response.CategoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryListPresenter : CategoryContract.Presenter {

    private lateinit var view: CategoryContract.View
    private lateinit var subscriptions: CompositeDisposable

    override fun loadData() {
//        view.showProgress(true)
        val subscribe = api.getCategoryList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: CategoryResponse? ->
                    view.showProgress(false)
                    list?.let { view.loadDataSuccess(it) }
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
        subscriptions = CompositeDisposable()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }
}