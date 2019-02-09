package com.gdn.rentalan.ui.transaction

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.response.Transaction
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.transaction.model.TransactionMapper
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionMyPresenter @Inject constructor(private val api: ApiInterface) :
        BasePresenter(), TransactionMyContract.Presenter {

    private lateinit var view: TransactionMyContract.View
    private val subscriptions = CompositeDisposable()

    override fun fetchData() {
        val subscribe = api.getTransactionListRenter().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestListResponse<Transaction> ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "sukses nihh")

                    val listItems: MutableList<TransactionUiModel> = ArrayList()
                    list.data.forEach { contentElement ->
                        listItems.add(TransactionMapper.mapToTransactionUiModel(contentElement))
                    }
                    view.fetchDataSuccess(listItems)

                    if (list.data.isEmpty()) {
                        view.showNoData()
                    }

                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun attachView(view: TransactionMyContract.View) {
        this.view = view
    }

    override fun attach() {
        super.attach()
        fetchData()
    }
}