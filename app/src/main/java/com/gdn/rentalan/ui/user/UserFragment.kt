package com.gdn.rentalan.ui.user

//class UserFragment : Fragment(), UserContract.View, UserListAdapter.onItemClickListener {
//
//    @Inject
//    lateinit var presenter: UserContract.Presenter
//
//    private lateinit var rootView: View
//
//    fun newInstance(): UserFragment {
//        return UserFragment()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        injectDependency()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        rootView = inflater.inflate(R.layout.fragment_user, container, false)
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
//    override fun loadDataSuccess(list: UserResponse) {
//        val adapter = activity?.let {
//            list.data?.toMutableList()?.let { users ->
//                UserListAdapter(it, users, this)
//            }
//        }
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        recyclerView.adapter = adapter
//    }
//
//    override fun itemRemoveClick(post: User) {
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