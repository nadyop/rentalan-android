package com.gdn.rentalan.ui.category

import android.os.Bundle
import com.gdn.rentalan.R
import com.gdn.rentalan.api.request.CategoryRequest
import com.gdn.rentalan.databinding.ActivityAddCategoryBinding
import com.gdn.rentalan.ui.base.BaseActivity
import javax.inject.Inject

class AddCategoryActivity : BaseActivity() {

    private lateinit var binding: ActivityAddCategoryBinding

    @Inject
    lateinit var addCategoryPresenter: AddCategoryPresenter

    private lateinit var name: String
    private lateinit var desc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
    }


    private fun getPresenter(): AddCategoryPresenter {
        return addCategoryPresenter
    }

    private fun sendCategory() {
        binding.btSave.setOnClickListener {
            name = binding.etCategoryName.toString()
            desc = binding.etCategoryDesc.toString()

            getPresenter().addCategory(name, desc)
        }
    }

}