package com.gdn.rentalan.util

import android.content.Context
import android.content.Intent
import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.category.CategoryAddActivity
import com.gdn.rentalan.ui.main.MainActivity
import com.gdn.rentalan.ui.product.ProductDetailActivity
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

interface Router {
    companion object {

        fun goToMain(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        fun goToCategoryAdd(context: Context) {
            val intent = Intent(context, CategoryAddActivity::class.java)
            context.startActivity(intent)
        }

        fun goToCategoryList(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        fun gotoProductDetail(context: Context,
                              productUiModel: ProductDetailUiModel) {
            val intent = ProductDetailActivity.Companion.newInstance(context, productUiModel)
            context.startActivity(intent)
        }
    }
}