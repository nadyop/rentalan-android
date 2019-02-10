package com.gdn.rentalan.util

import android.content.Context
import android.content.Intent
import com.gdn.rentalan.ui.account.profile.AccountFragment
import com.gdn.rentalan.ui.account.profile.AccountUiModel
import com.gdn.rentalan.ui.account.profile.edit.AccountEditActivity
import com.gdn.rentalan.ui.category.CategoryAddActivity
import com.gdn.rentalan.ui.login.LoginActivity
import com.gdn.rentalan.ui.main.admin.MainActivity
import com.gdn.rentalan.ui.main.user.UserMainActivity
import com.gdn.rentalan.ui.product.admin.ProductDetailActivity
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel
import com.gdn.rentalan.ui.product.renter.ProductActivityCheckout
import com.gdn.rentalan.ui.register.email.RegisterEmailActivity
import com.gdn.rentalan.ui.register.otp.RegisterOtpActivity
import com.gdn.rentalan.ui.register.profile.RegisterProfileActivity
import com.gdn.rentalan.ui.transaction.TransactionDetailActivity
import com.gdn.rentalan.ui.transaction.model.TransactionUiModel
import com.gdn.rentalan.ui.user.UserDetailActivity
import com.gdn.rentalan.ui.user.model.UserDetailUiModel

interface Router {
    companion object {

        private val PARAM_MAIN_MENU = "mainMenu"

        fun goToMain(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
//            val intent = MainActivity.Companion.newInstance(context)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        fun goToUserMain(context: Context) {
            val intent = Intent(context, UserMainActivity::class.java)
            context.startActivity(intent)
        }

        fun goToLogin(context: Context) {
            val intent = LoginActivity.newInstance(context)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        fun goToRegister(context: Context) {
            val intent = Intent(context, RegisterEmailActivity::class.java)
            context.startActivity(intent)
        }

        fun goToRegisterProfile(context: Context) {
            val intent = Intent(context, RegisterProfileActivity::class.java)
            context.startActivity(intent)
        }

        fun goToCategoryAdd(context: Context) {
            val intent = Intent(context, CategoryAddActivity::class.java)
            context.startActivity(intent)
        }

        fun goToRegisterOtp(context: Context) {
            val intent = Intent(context, RegisterOtpActivity::class.java)
            context.startActivity(intent)
        }

        fun goToRegisterEmail(context: Context) {
            val intent = Intent(context, RegisterEmailActivity::class.java)
            context.startActivity(intent)
        }

        fun goToCategoryList(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(PARAM_MAIN_MENU, MainActivity.CATEGORY_FRAGMENT_INDEX)
            context.startActivity(intent)
        }

        fun gotoProductDetail(context: Context,
                              productUiModel: ProductDetailUiModel) {
            val intent = ProductDetailActivity.newInstance(context, productUiModel)
            context.startActivity(intent)
        }

        fun gotoUserDetail(context: Context,
            userUiModel: UserDetailUiModel) {
            val intent = UserDetailActivity.newInstance(context, userUiModel)
            context.startActivity(intent)
        }

        fun gotoProductCheckoutDetail(context: Context,
                              productUiModel: ProductDetailUiModel) {
            val intent = ProductActivityCheckout.newInstance(context, productUiModel)
            context.startActivity(intent)
        }

        fun gotoProductTransactionDetail(
            context: Context, transactionUiModel: TransactionUiModel) {
            val intent = TransactionDetailActivity.newInstance(context, transactionUiModel)
            context.startActivity(intent)
        }

        fun gotoAccountEdit(
            context: Context, accountUiModel: AccountUiModel) {
            val intent = AccountEditActivity.newInstance(context, accountUiModel)
            context.startActivity(intent)
        }
    }
}