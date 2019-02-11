package com.gdn.rentalan.ui.account.profile.edit

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.UserVerifyRequest
import com.gdn.rentalan.databinding.ActivityProfileEditBinding
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*
import javax.inject.Inject

class AccountEditActivity : BaseActivity(), AccountEditContract.View {

    companion object {
        private const val ACCOUNT = "detail"
        fun newInstance(context: Context, detail: AccountUiModel): Intent {
            val intent = Intent(context, AccountEditActivity::class.java)
            intent.putExtra(
                    ACCOUNT, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: AccountEditContract.Presenter
    private lateinit var binding: ActivityProfileEditBinding
    private var detail: AccountUiModel? = null
    private var valueKtp: String = "0"
    private var valueSelf: String = "0"

    private var selfFile: File? = null
    private var ktpFile: File? = null
    private var ktpFileName: String? = null
    private var selfFileName: String? = null


    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)
        presenter.attachView(this)
        presenter.getDetail()

        userAction()
    }

    private fun selectImage(context: Context) {
        val options = arrayOf<CharSequence>("Take Photo", "Cancel")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose image")

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
        if (this.valueKtp == "1") {
            if (resultCode != Activity.RESULT_CANCELED) {
                when (requestCode) {
                    0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                        val ktpImage = data.extras?.get("data") as Bitmap
                        binding.ivUserKtp.setImageBitmap(ktpImage)
                        ktpFile = convertBitmapToFile(ktpImage)
                        ktpFileName = ktpFile?.name.toString()
                    }
                }
            }
        } else {
            if (resultCode != Activity.RESULT_CANCELED) {
                when (requestCode) {
                    0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                        val selfImage = data.extras?.get("data") as Bitmap
                        binding.ivUserSelf.setImageBitmap(selfImage)
                        selfFile = convertBitmapToFile(selfImage)
                        selfFileName = selfFile?.name.toString()
                    }
                }
            }
        }
    }

    fun convertBitmapToFile(photo: Bitmap): File {
        val file = File(this.cacheDir, UUID.randomUUID().toString() + ".jpg")
        val os: OutputStream
        try {
            os = BufferedOutputStream(FileOutputStream(file))
            photo.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file
    }


    private fun userAction() {

        binding.btUpload.setOnClickListener {
            this.valueKtp = "1"
            selectImage(this@AccountEditActivity)
        }

        binding.btUploadSelf.setOnClickListener {
            this.valueSelf = "1"
            selectImage(this@AccountEditActivity)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btSave.setOnClickListener {
            val req = UserVerifyRequest(
                    binding.etNik.text.toString(),
                    binding.etGender.text.toString(),
                    binding.etBirth.text.toString(),
                    binding.etAddress.text.toString(),
                    binding.etCity.text.toString(),
                    binding.etProvince.text.toString()
            )

            ktpFile?.let { ktp ->
                selfFile?.let { self ->
                    presenter.saveDetail(req, ktp, self)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.getDetail()
        detail?.let { setData(it) }
    }

    override fun setData(details: AccountUiModel) {

        with(binding) {
            etNik.setText(detail?.nik, TextView.BufferType.EDITABLE)
            etGender.setText(detail?.gender, TextView.BufferType.EDITABLE)
            etBirth.setText(detail?.birthDate, TextView.BufferType.EDITABLE)
            etAddress.setText(detail?.email, TextView.BufferType.EDITABLE)
            etCity.setText(detail?.city, TextView.BufferType.EDITABLE)
            etProvince.setText(detail?.province, TextView.BufferType.EDITABLE)
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
