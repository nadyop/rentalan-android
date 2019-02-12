package com.gdn.rentalan.ui.account.profile

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdn.rentalan.R
import com.gdn.rentalan.databinding.FragmentProfileBinding
import com.gdn.rentalan.ui.account.profile.verifyprofile.AccountEditActivity
import com.gdn.rentalan.ui.account.profile.editprofile.AccountEditProfileActivity
import com.gdn.rentalan.ui.base.BaseFragment
import com.gdn.rentalan.ui.main.user.UserMainActivity
import com.gdn.rentalan.util.Router
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AccountFragment : BaseFragment(), AccountContract.View {

    companion object {
        private const val ACCOUNT = "account"
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, UserMainActivity::class.java)
            return intent
        }
    }

    @Inject
    lateinit var presenter: AccountContract.Presenter
    private lateinit var binding: FragmentProfileBinding
    private var detail: AccountUiModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.getAccount()
        detail?.let { setData(it) }
        userAction()
    }

    override fun goToEditProfile(userId: String) {
        val intent = Intent(context, AccountEditActivity::class.java)
        intent.putExtra("userId", userId)
        context?.startActivity(intent)
    }

    override fun goToEditProfileVerify(userId: String) {
        val intent = Intent(context, AccountEditProfileActivity::class.java)
        intent.putExtra("userId", userId)
        context?.startActivity(intent)
    }

    private fun userAction() {
        binding.btEdit.setOnClickListener {
            presenter.goToEditUserId()
        }

        binding.btEditVerify.setOnClickListener {
            presenter.goToEditUserIdVerify()
        }

        binding.tvLogout.setOnClickListener {
            context?.let { it1 -> Router.goToLogin(it1) }
        }
    }

    override fun setData(items: AccountUiModel) {
        with(binding) {
            tvUsername.text = items.username
            tvSurename.text = items.sureName
            tvEmail.text = items.email
            tvPhone.text = items.phoneNumber
            tvAddress.text = items.address
            tvProvince.text = items.province
            tvCity.text = items.city
            tvGender.text = items.gender
            tvBirth.text = items.birthDate
        }

        if (items.status == "verify") {
            val colorBackground = context?.let { ContextCompat.getColor(it, R.color.state_done) }
            binding.tvStatus.text = getString(R.string.message_verify)
            colorBackground?.let { binding.llStatus.setBackgroundColor(it) }
            binding.btEdit.visibility = View.GONE
            binding.btEditVerify.visibility = View.VISIBLE
        } else {
            binding.tvStatus.text = getString(R.string.message_verify_yet)
        }
    }

    override fun goToEdit() {
        context?.let { detail?.let { it1 -> Router.gotoAccountEdit(it, it1) } }
    }

    override fun showProgress(show: Boolean) {
        super.showProgress(show)
        if (show) {
            binding.llStatus.visibility = View.GONE
            binding.tlProfile.visibility = View.GONE
        } else {
          binding.llStatus.visibility = View.VISIBLE
          binding.tlProfile.visibility = View.VISIBLE
        }
    }
}