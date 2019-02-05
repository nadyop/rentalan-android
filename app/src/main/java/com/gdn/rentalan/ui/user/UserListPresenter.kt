package com.gdn.rentalan.ui.user

//class UserListPresenter : UserContract.Presenter {
//    private val subscriptions = CompositeDisposable()
//    private val api: ApiInterface = ApiInterface.create()
//    private lateinit var view: UserContract.View
//
//    override fun loadData() {
//        val subscribe = api.getUserList().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ list: UserResponse? ->
//                    view.showProgress(false)
//                    list?.let { view.loadDataSuccess(it) }
//                }, { error ->
//                    view.showProgress(false)
//                    view.showErrorMessage(error.localizedMessage)
//                })
//
//        subscriptions.add(subscribe)
//    }
//
//    override fun deleteItem(item: User) {
//    }
//
//    override fun subscribe() {
//
//    }
//
//    override fun unsubscribe() {
//        subscriptions.clear()
//    }
//
//    override fun attach(view: UserContract.View) {
//        this.view = view
//    }
//}