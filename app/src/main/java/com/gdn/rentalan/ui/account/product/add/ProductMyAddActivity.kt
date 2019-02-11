package com.gdn.rentalan.ui.account.product.add

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.ProductVerifyRequest
import com.gdn.rentalan.databinding.ActivityAddProductMyBinding
import com.gdn.rentalan.ui.base.BaseActivity
import com.gdn.rentalan.ui.base.BaseContract
import com.gdn.rentalan.ui.category.CategoryUiModel
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.util.Router
import dagger.android.AndroidInjection
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ProductMyAddActivity : BaseActivity(), ProductMyAddContract.View {

    companion object {
        private const val MYDETAIL = "mydetail"
        fun newInstance(context: Context, detail: ProductDetailUiModel): Intent {
            val intent = Intent(context, ProductMyAddActivity::class.java)
            intent.putExtra(MYDETAIL, detail) //from @Parcelize
            return intent
        }
    }

    @Inject
    lateinit var presenter: ProductMyAddContract.Presenter
    lateinit var binding: ActivityAddProductMyBinding
    private var detail: ProductDetailUiModel? = null
    private var categoryUiModel: CategoryUiModel? = null
    private var categories: List<CategoryUiModel>? = null


    private var image: File? = null
    private var imageFileName: String? = null

    override fun getPresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product_my)
        presenter.attachView(this)

        detail = intent.getParcelableExtra(MYDETAIL)
        detail?.id?.let {
            presenter.getDetail(it)
        }
        userAction()
        binding.btSave.setOnClickListener {
            submit()
        }

        Log.d("aaazz", detail.toString())
        presenter.getCategory()
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
        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val productImage = data.extras?.get("data") as Bitmap
                    binding.ivProduct.setImageBitmap(productImage)
                    image = convertBitmapToFile(productImage)
                    imageFileName = image?.name.toString()
                }
            }
        }
    }

    private fun convertBitmapToFile(photo: Bitmap): File {
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
        Log.d("detail", detail.toString())
//        detail?.let { setData(it) }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btUpload.setOnClickListener {
            selectImage(this@ProductMyAddActivity)
        }

        binding.btSave.setOnClickListener {
            val req = ProductVerifyRequest(
                    binding.etProductName.text.toString(),
                    binding.etDesc.text.toString(),
                    binding.etPricePerday.text.toString(),
                    binding.etStock.text.toString().toInt(),
                    binding.etDp.text.toString().toInt(),
                    binding.etLateCharge.text.toString().toInt()
            )

            image?.let { img ->
                presenter.addProductByOwner(req, img)
            }
        }
    }

    override fun setData(details: ProductDetailUiModel) {
        with(binding) {
            etProductName.setText(details.name)
            etDesc.setText(details.description)
            etPricePerday.setText(details.pricePerDay)
            etStock.setText(details.stock)
            etDp.setText(details.downPayment)
            etLateCharge.setText(details.lateCharge)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.containerSecond.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.containerSecond.visibility = View.VISIBLE
        }
    }

    override fun gotoUserMain() {
        Router.goToUserMain(this)
        showToast("Barang berhasil disimpan", Toast.LENGTH_LONG)
    }

    override fun showCategories(categories: List<CategoryUiModel>) {
        this.categories = categories

        val categoriesData = ArrayList<String>()
        for (category in categories) {
            categoriesData.add(category.name)
        }

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.item_spinner, categoriesData)
        binding.spCategory.adapter = arrayAdapter
    }

    private fun submit(){
        val selectedName = binding.spCategory.selectedItem.toString()
        val selectedCategoryId = categories?.single {
            it.name == selectedName
        }?.id

        val req = ProductVerifyRequest(
            binding.etProductName.text.toString(),
            binding.etDesc.text.toString(),
            selectedCategoryId,
            binding.etPricePerday.text.toString().toInt(),
            binding.etStock.text.toString().toInt(),
            binding.etDp.text.toString().toInt(),
            binding.etLateCharge.text.toString().toInt()
        )

        image?.let { presenter.addProductByOwner(req, it) }
    }
}