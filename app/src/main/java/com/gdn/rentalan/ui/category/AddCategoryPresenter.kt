package com.gdn.rentalan.ui.category

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.request.CategoryRequest
import com.gdn.rentalan.api.response.RestCommonResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddCategoryPresenter : AddCategoryContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiInterface = ApiInterface.create()
    private lateinit var view: AddCategoryContract.View

    override fun addCategory(name: String, desc: String) {
        val request = CategoryRequest(name, desc)

        val subscribe = api.addCategory(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestCommonResponse? ->
                    list?.let {
                        Log.d("AAAA", "category created")
                    }
                }, { error ->
                    Log.d("AAAA", error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: AddCategoryContract.View) {
        this.view = view
    }

}