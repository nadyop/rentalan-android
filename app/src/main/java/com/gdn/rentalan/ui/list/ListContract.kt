//package com.gdn.rentalan.ui.list
//
//import com.gdn.rentalan.models.Post
//import com.gdn.rentalan.ui.base.BaseContract
//
//class ListContract {
//
//    interface View: BaseContract.View {
//        fun showProgress(show: Boolean)
//        fun showErrorMessage(error: String)
//        fun loadDataSuccess(list: List<Post>)
//    }
//
//    interface Presenter: BaseContract.Presenter<View> {
//        fun loadData()
//        fun deleteItem(item: Post)
//    }
//}