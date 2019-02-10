package com.gdn.rentalan.ui.account.profile.edit

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.UserRequest
import com.gdn.rentalan.databinding.ActivityProfileEditBinding
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import javax.inject.Inject

class AccountEditActivity : BaseActivity(), AccountEditContract.View {

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

  override fun getPresenter(): BaseContract.Presenter? {
    return presenter
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)
    presenter.attachView(this)
    presenter.getDetail()

//    detail?.let { setData(it) }
    userAction()
  }

  private fun selectImage(context: Context) {
    val options = arrayOf<CharSequence>("Take Photo", "Cancel")

    val builder = AlertDialog.Builder(context)
    builder.setTitle("Choose your profile picture")

    builder.setItems(options) { dialog, item ->
      if (options[item] == "Take Photo") {
        val takePicture = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, 0)

      } else if (options[item] == "Cancel") {
        dialog.dismiss()
      }
    }
    builder.show()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode != Activity.RESULT_CANCELED) {
      when (requestCode) {
        0 -> if (resultCode == Activity.RESULT_OK && data != null) {
          val ktpImage = data.extras?.get("data") as Bitmap
          binding.ivUserKtp.setImageBitmap(ktpImage)
        }
      }
    }
  }

  private fun userAction() {

    binding.btUpload.setOnClickListener {
      selectImage(this@AccountEditActivity)
    }

    binding.btUploadSelf.setOnClickListener {
      selectImage(this@AccountEditActivity)
    }

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
//            etKtp.text.toString()
//            etSelf.text.toString()
          }
      ))
    }
  }

  override fun onStart() {
    super.onStart()
    presenter.getDetail()
    detail?.let { setData(it) }
  }

  override fun setData(details: AccountUiModel) {
    binding.etSurename.setText(details.sureName)
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
      binding.cv3.visibility = View.GONE
      binding.btSave.visibility = View.GONE
      binding.progressBar.visibility = View.VISIBLE
    } else {
      binding.cv1.visibility = View.VISIBLE
      binding.cv2.visibility = View.VISIBLE
      binding.cv3.visibility = View.VISIBLE
      binding.btSave.visibility = View.VISIBLE
      binding.progressBar.visibility = View.GONE
    }
  }
}
