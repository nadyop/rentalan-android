package com.gdn.rentalan.ui.transaction.detail

import android.util.Log
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.api.RestSingleResponse
import com.gdn.rentalan.api.response.TransactionDetail
import com.gdn.rentalan.api.response.User
import com.gdn.rentalan.ui.base.BasePresenter
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionDetailPresenter @Inject constructor(private val api: ApiInterface) :
BasePresenter(), TransactionDetailContract.Presenter {

  private lateinit var view: TransactionDetailContract.View
  private val subscriptions = CompositeDisposable()
  private var renterId: String = ""

  override fun getData(id: String) {
    view.showProgress(true)
    val subscribe = api.getTransactionDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response: RestSingleResponse<TransactionDetail> ->
          response.data?.let {
            this.renterId = it.renterId
            val items = TransactionUiModel(
                it.id.orEmpty(),
                it.downPayment,
                it.endDate.orEmpty(),
                it.lateCharge,
                it.quantity,
                it.startDate,
                it.totalPayment,
                it.status,
                it.ownerPhoneNumber,
                it.description,
                it.ownerId,
                it.pricePerDay,
                it.categoryName,
                it.productImage,
                it.ownerName,
                it.name,
                it.ownerCity,
                it.stock
            )
            getRenterDetail(renterId)
            view.setData(items)
          }
          view.showProgress(false)
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })
    subscriptions.add(subscribe)
  }

  private fun getRenterDetail(renterId: String){
    val subscribe = api.getUserDetail(renterId).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response: RestSingleResponse<User> ->
          Log.d("AAAAZ", "sukses nihh")
          Log.d("AAAAZgetData", response.data.toString())
          response.data?.let {
            val items = TransactionUiModel(
                renterName = it.sureName,
                renterPhone = it.phoneNumber,
                renterCity = it.city
            )
            view.setData(items)
          }
          view.showProgress(false)
        }, { error ->
          view.showProgress(false)
          Log.d("AAAAZ", "error nihh + ==== + ${error.message} + ==== + ${error.cause}")
          view.showErrorMessage(error.localizedMessage)
        })
    subscriptions.add(subscribe)
  }

  override fun rentAcceptByOwner() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun attachView(view: TransactionDetailContract.View) {
    this.view = view
  }
}
