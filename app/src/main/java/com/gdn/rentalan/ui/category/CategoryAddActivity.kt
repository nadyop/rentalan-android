package com.gdn.rentalan.ui.category

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityAddCategoryBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class CategoryAddActivity : BaseActivity(), CategoryAddContract.View, HasActivityInjector {

    @Inject
    lateinit var presenter: CategoryAddContract.Presenter
    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var binding: ActivityAddCategoryBinding

    private var actionButtonClickListener: View.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_category)
        userAction()
    }

    override fun getPresenter(): BaseContract.Presenter {
        return presenter
    }

    override fun goToCategoryList() {

        Router.goToCategoryList(this)
        super.onBackPressed()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return this.activityInjector
    }

    private fun userAction() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sendDataListener(View.OnClickListener {
            presenter.sendData(
                    binding.etCategory.text.toString(),
                    binding.etDesc.text.toString()
            )
        })
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.btSave.setOnClickListener(listener)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}