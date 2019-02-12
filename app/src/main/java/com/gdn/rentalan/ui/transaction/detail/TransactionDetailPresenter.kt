package com.gdn.rentalan.ui.transaction.detail

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestCommonResponse
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.Latecharge
import com.gdn.rentalan.api.response.TransactionDetail
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.util.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionDetailPresenter @Inject constructor(private val api: ApiInterface, loginRepository: LoginRepository) :
        BasePresenter(), TransactionDetailContract.Presenter {

    private lateinit var view: TransactionDetailContract.View
    private val subscriptions = CompositeDisposable()
    private var renterId: String = ""
    private var ownerId: String = ""
    private var userId = loginRepository.userId

    override fun getData(id: String) {
        view.showProgress(true)
        val subscribe = api.getTransactionDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<TransactionDetail> ->
                    response.data?.let {
                        this.renterId = it.renterId
                        this.ownerId = it.ownerId
                        val items = it.product?.pricePerDay?.let { it1 ->
                            TransactionUiModel(
                                    id = it.id.orEmpty(),
                                    downPayment = it.downPayment,
                                    endDate = it.endDate.orEmpty(),
                                    lateCharge = it.lateCharge,
                                    quantity = it.quantity,
                                    startDate = it.startDate,
                                    totalPayment = it.totalPayment,
                                    status = it.status,
                                    ownerPhoneNumber = it.product.ownerPhoneNumber,
                                    description = it.product.description,
                                    ownerId = it.ownerId,
                                    pricePerDay = it1,
                                    categoryName = it.product.categoryName,
                                    productImage = it.product.productImage,
                                    ownerName = it.product.ownerName,
                                    name = it.product.name,
                                    ownerCity = it.product.ownerCity,
                                    renterId = it.renterId
                            )
                        }
                        items?.let { it1 -> view.setData(it1) }
                    }
                    this.getRenterDetail(renterId)
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    private fun getRenterDetail(renterId: String) {
        val subscribe = api.getUserDetail(renterId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: RestSingleResponse<User> ->
                    response.data?.let {
                        val items = TransactionUiModel(
                                renterName = it.sureName.toString(),
                                renterPhone = it.phoneNumber.toString(),
                                renterCity = it.city.toString()
                        )
                        view.setData(items)
                        Log.d("renter detail", items.toString())
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun rentAcceptByOwner(transactionId: String, isOwner: Boolean) {
        val subscribe = api.acceptRentProduct(transactionId, isOwner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestCommonResponse ->
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun attachView(view: TransactionDetailContract.View) {
        this.view = view
    }

    override fun returnProduct(transactionId: String) {
        val subscribe = api.returnRentProduct(transactionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: RestSingleResponse<Latecharge> ->
                    list.data?.let {
                        it.lateCharge?.toInt()?.let { it1 -> view.showLateCharge(it1) }
                    }
                    view.showProgress(false)
                }, { error ->
                    view.showProgress(false)
                    Log.d("AAAAZ", "error add nihh + ==== + ${error.message} + ==== + ${error.cause}")
                    view.showErrorMessage(error.localizedMessage)
                })
        subscriptions.add(subscribe)
    }
}
