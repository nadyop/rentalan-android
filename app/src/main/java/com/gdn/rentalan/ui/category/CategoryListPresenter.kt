package com.gdn.rentalan.ui.category

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.models.Category
import com.gdn.rentalan.models.CategoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryListPresenter : CategoryContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private val api: ApiInterface = ApiInterface.create()
    private lateinit var view: CategoryContract.View

    override fun loadData() {
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

    override fun deleteItem(item: Category) {
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }
}