package com.gdn.rentalan.ui.user

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.ActivityUserDetailAdminBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.user.model.UserDetailUiModel
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class UserDetailActivity : BaseActivity(), UserDetailContract.View {

    companion object {
        private const val DETAIL = "detail"
        fun newInstance(context: Context, detail: UserDetailUiModel): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(DETAIL, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: UserDetailContract.Presenter
    private lateinit var binding: ActivityUserDetailAdminBinding
    private var detail: UserDetailUiModel? = null
    private var actionButtonClickListener: View.OnClickListener? = null

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail_admin)
        presenter.attachView(this)
        detail = intent.getParcelableExtra(DETAIL)
        Log.d("AAAAZ", detail.toString())
        detail?.id?.let {
            presenter.getData(it)
            Log.d("AAAAZ", "ini tu ga null")
        }
        userAction()
    }

    private fun userAction(){
        Log.d("AAAAZ", "userAction")
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        sendDataListener(View.OnClickListener {
            Log.d("AAAAZuserId = ", detail?.id.orEmpty())
          detail = intent.getParcelableExtra(DETAIL)
          presenter.verification(
                detail?.id.orEmpty(), true.toString()
            )
        })
    }

    private fun sendDataListener(listener: View.OnClickListener) {
        this.actionButtonClickListener = listener
        binding.buttonRight.setOnClickListener(listener)
    }

    override fun setData(content: UserDetailUiModel) {
        with(binding) {
            tvSurename.text = content.sureName
            tvEmail.text = content.email
            tvPhone.text = content.phoneNumber
            tvGender.text = content.gender
            tvBirth.text = content.birthDate
            tvProvince.text = content.province
            tvCity.text = content.city
            tvAddress.text = content.address

        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.container.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.container.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun goToUserList() {
        Router.goToMain(this)
        super.onBackPressed()
    }
}