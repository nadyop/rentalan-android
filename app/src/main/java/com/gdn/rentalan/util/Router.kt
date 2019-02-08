package com.gdn.rentalan.util

import android.content.Context
import android.content.Intent
import com.gdn.rentalan.ui.category.CategoryAddActivity
import com.gdn.rentalan.ui.main.MainActivity
import com.gdn.rentalan.ui.product.ProductActivityCheckout
import com.gdn.rentalan.ui.product.admin.ProductDetailActivity
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.ui.user.UserDetailActivity
import com.gdn.rentalan.ui.user.model.UserDetailUiModel

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

        fun gotoUserDetail(context: Context,
            userUiModel: UserDetailUiModel) {
            val intent = UserDetailActivity.Companion.newInstance(context, userUiModel)
            context.startActivity(intent)
        }

        fun gotoProductCheckoutDetail(context: Context,
                              productUiModel: ProductDetailUiModel) {
            val intent = ProductActivityCheckout.Companion.newInstance(context, productUiModel)
            context.startActivity(intent)
        }
    }
}