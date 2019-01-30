//package com.gdn.rentalan.ui.list
//
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.gdn.rentalan.R
//import com.gdn.rentalan.di.component.DaggerFragmentComponent
//import com.gdn.rentalan.di.module.FragmentModule
//import com.gdn.rentalan.models.DetailsViewModel
//import com.gdn.rentalan.models.Post
//import kotlinx.android.synthetic.main.fragment_list.*
//import javax.inject.Inject
//
//class ListFragment: Fragment(), ListContract.View, ListAdapter.onItemClickListener {
//
//    @Inject
//    lateinit var presenter: ListContract.Presenter
//
//    private lateinit var rootView: View
//
//    fun newInstance(): ListFragment {
//        return ListFragment()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        injectDependency()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        rootView = inflater.inflate(R.layout.fragment_list, container, false)
//        return rootView
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        presenter.attach(this)
//        presenter.subscribe()
//        initView()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        presenter.unsubscribe()
//    }
//
//    override fun showProgress(show: Boolean) {
//        if (show) {
//            progressBar.visibility = View.VISIBLE
//        } else {
//            progressBar.visibility = View.GONE
//        }
//    }
//
//    override fun showErrorMessage(error: String) {
//        Log.e("Error", error)
//    }
//
//    override fun loadDataSuccess(list: List<Post>) {
//        val adapter = activity?.let { ListAdapter(it, list.toMutableList(), this) }
//        recyclerView!!.layoutManager = LinearLayoutManager(activity)
//        recyclerView!!.adapter = adapter
//    }
//
//    override fun itemRemoveClick(post: Post) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun itemDetail(postId: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private fun injectDependency() {
//        val listComponent = DaggerFragmentComponent.builder()
//                .fragmentModule(FragmentModule())
//                .build()
//
//        listComponent.inject(this)
//    }
//
//    private fun initView() {
//        presenter.loadData()
//    }
//
//    companion object {
//        val TAG: String = "ListFragment"
//    }
//}