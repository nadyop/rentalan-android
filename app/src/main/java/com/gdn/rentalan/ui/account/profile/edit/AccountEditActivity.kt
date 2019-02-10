package com.gdn.rentalan.ui.account.profile.edit

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.UserRequest
import com.gdn.rentalan.databinding.ActivityProfileEditBinding
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.MediaUtils
import com.gdn.rentalan.util.Router
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import java.io.File
import javax.inject.Inject

class AccountEditActivity : BaseActivity(), AccountEditContract.View, MediaUtils.GetImg {

  companion object {
    private const val TRANSACTION = "detail"
    fun newInstance(context: Context, detail: AccountUiModel): Intent {
      val intent = Intent(context, AccountEditActivity::class.java)
      intent.putExtra(
          TRANSACTION, detail) //from @Parcelize
      return intent
    }
  }

  @Inject
  lateinit var presenter: AccountEditContract.Presenter
  private lateinit var binding: ActivityProfileEditBinding
  private var detail: AccountUiModel? = null
  internal lateinit var mMediaUtils: MediaUtils

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)
    presenter.attachView(this)

    detail?.let { setData(it) }
    userAction()

    mMediaUtils = MediaUtils(this)

  }

  private fun userAction() {
    binding.toolbar.setNavigationOnClickListener {
      onBackPressed()
    }

    binding.btSave.setOnClickListener {
      presenter.saveDetail(UserRequest(
          with(binding) {
            etNik.text.toString()
            etPhone.text.toString()
            etAddress.text.toString()
            etGender.text.toString()
            etProvince.text.toString()
            etCity.text.toString()
            etSurename.text.toString()
            etBirth.text.toString()
            etBirth.text.toString()
            etSurename.text.toString()
            etKtp.text.toString()
            etSelf.text.toString()
          }
      ))
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
      grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    mMediaUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    data?.let { mMediaUtils.onActivityResult(requestCode, resultCode, it) }
  }

  override fun imgdata(imgPath: String?) {
    Log.d("AccountEditActivity", "imgdata: $imgPath")
    Picasso.get().load(File(imgPath)).into(binding.ivSelf)
  }

  fun selectImg(view: View) {
    // select image button clicked
    mMediaUtils.openImageDialog()
  }

  private fun uploadKtp() {
    binding.btUploadSelf.setOnClickListener {
      mMediaUtils.openImageDialog()
    }
  }
  override fun setData(details: AccountUiModel) {
    with(binding) {
      etSurename.setText(detail?.sureName, TextView.BufferType.EDITABLE)
      etEmail.setText(detail?.email, TextView.BufferType.EDITABLE)
      etPhone.setText(detail?.phoneNumber, TextView.BufferType.EDITABLE)
      etAddress.setText(detail?.email, TextView.BufferType.EDITABLE)
      etProvince.setText(detail?.province, TextView.BufferType.EDITABLE)
      etCity.setText(detail?.city, TextView.BufferType.EDITABLE)
      etGender.setText(detail?.gender, TextView.BufferType.EDITABLE)
      etBirth.setText(detail?.birthDate, TextView.BufferType.EDITABLE)
      etNik.setText(detail?.nik, TextView.BufferType.EDITABLE)
    }
  }

  override fun goToMainPage() {
    Router.goToUserMain(this)
    showToast("Data diri berhasil disimpan", Toast.LENGTH_LONG)
  }

  override fun showProgress(show: Boolean) {
    if (show) {
      binding.cv1.visibility = View.GONE
      binding.cv2.visibility = View.GONE
      binding.btSave.visibility = View.GONE
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.cv1.visibility = View.VISIBLE
      binding.cv2.visibility = View.VISIBLE
      binding.btSave.visibility = View.VISIBLE
      binding.progressBar.visibility = View.GONE
    }
  }
}
