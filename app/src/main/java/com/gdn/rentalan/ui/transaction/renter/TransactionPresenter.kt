package com.gdn.rentalan.ui.transaction.renter

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestListResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.Transaction
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.transaction.model.TransactionMapper
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.ui.user.model.UserDetailUiModel
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionPresenter @Inject constructor(private val api: ApiInterface,
    private val loginRepository: LoginRepository) :
        BasePresenter(), TransactionContract.Presenter {

    private lateinit var view: TransactionContract.View
    private val subscriptions = CompositeDisposable()

    override fun fetchData() {
        view.showProgress(true)
        val subscribe = api.getTransactionListRenter(loginRepository.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestListResponse<Transaction> ->
                    view.showProgress(false)
                    Log.d("List owner", list.data.toString())

                    var listItems: MutableList<TransactionUiModel> = ArrayList()
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

    override fun attachView(view: TransactionContract.View) {
        this.view = view
    }

    override fun attach() {
        super.attach()
        fetchData()
    }
}