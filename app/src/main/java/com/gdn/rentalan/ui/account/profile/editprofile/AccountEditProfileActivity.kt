package com.gdn.rentalan.ui.account.profile.editprofile

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.UserUpdateRequest
import com.gdn.rentalan.databinding.ActivityProfileEditVerifyBinding
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

class AccountEditProfileActivity : BaseActivity() , AccountEditProfileContract.View{

    companion object {
        private const val ACCOUNTVERIFY = "detail"
        fun newInstance(context: Context, detail: AccountUiModel): Intent {
            val intent = Intent(context, AccountEditProfileActivity::class.java)
            intent.putExtra(
                    ACCOUNTVERIFY, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: AccountEditProfileContract.Presenter
    private lateinit var binding: ActivityProfileEditVerifyBinding
    private var detail: AccountUiModel? = null

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit_verify)
        presenter.attachView(this)
        presenter.getDetail()
        detail?.let { setData(it) }
        userAction()
    }

    private fun userAction() {
        binding.etBirth.setOnClickListener {
            val c = Calendar.getInstance()
            c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val mDatePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    binding.etBirth.setText(String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear + 1,year))
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }, mYear, mMonth, mDay)
            c.set(Calendar.YEAR, 1980)
            c.set(Calendar.MONTH, 0)
            c.set(Calendar.DAY_OF_MONTH, 1)
            mDatePickerDialog.show()
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btSave.setOnClickListener {
            val request = UserUpdateRequest(
                    binding.etPhone.text.toString(),
                    binding.etSurename.text.toString(),
                    binding.etNik.text.toString(),
                    binding.etGender.text.toString(),
                    binding.etBirth.text.toString(),
                    binding.etAddress.text.toString(),
                    binding.etCity.text.toString(),
                    binding.etProvince.text.toString()
            )
            presenter.saveDetail(request)
        }
    }

    override fun setData(details: AccountUiModel) {
        with(binding) {
            etPhone.setText(details.phoneNumber)
            etSurename.setText(details.sureName)
            etNik.setText(details.nik)
            etGender.setText(details.gender)
            etBirth.setText(details.birthDate)
            etAddress.setText(details.address)
            etCity.setText(details.city)
            etProvince.setText(details.province)
        }
    }

    override fun goToMainPage() {
        Router.goToUserMain(this)
        showToast("Data diri berhasil disimpan", Toast.LENGTH_LONG)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.cv2.visibility = View.GONE
            binding.btSave.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.cv2.visibility = View.VISIBLE
            binding.btSave.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}