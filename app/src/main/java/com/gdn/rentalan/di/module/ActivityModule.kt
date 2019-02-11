package com.gdn.rentalan.di.module

import com.gdn.rentalan.di.module.feature.*
import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.account.product.ProductMyFragment
import com.gdn.rentalan.ui.account.product.add.ProductMyAddActivity
import com.gdn.rentalan.ui.account.profile.AccountFragment
import com.gdn.rentalan.ui.account.profile.edit.AccountEditActivity
import com.gdn.rentalan.ui.account.profile.editprofile.AccountEditProfileActivity
import com.gdn.rentalan.ui.category.CategoryAddActivity
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.dashboard.DashboardFragment
import com.gdn.rentalan.ui.login.LoginActivity
import com.gdn.rentalan.ui.main.admin.MainActivity
import com.gdn.rentalan.ui.main.user.UserMainActivity
import com.gdn.rentalan.ui.product.renter.ProductActivityCheckout
import com.gdn.rentalan.ui.product.admin.ProductDetailActivity
import com.gdn.rentalan.ui.product.admin.ProductFragment
import com.gdn.rentalan.ui.register.email.RegisterEmailActivity
import com.gdn.rentalan.ui.register.profile.RegisterProfileActivity
import com.gdn.rentalan.ui.search.SearchActivity
import com.gdn.rentalan.ui.transaction.TransactionDetailActivity
import com.gdn.rentalan.ui.transaction.TransactionFragment
import com.gdn.rentalan.ui.transaction.TransactionMyFragment
import com.gdn.rentalan.ui.user.UserDetailActivity
import com.gdn.rentalan.ui.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun provideRegisterEmailActivity(): RegisterEmailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun provideRegisterProfileActivity(): RegisterProfileActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideUserMainActivity(): UserMainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [CategoryModule::class])
    abstract fun provideCategoryFragment(): CategoryFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [CategoryModule::class])
    abstract fun provideCategoryAddActivity(): CategoryAddActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun provideUserFragment(): UserFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun provideProductFragment(): ProductFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun provideProductDetailActivity(): ProductDetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun provideUserDetailActivity(): UserDetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideDashboardFragment(): DashboardFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun provideProductActivityCheckout(): ProductActivityCheckout

    @ActivityScope
    @ContributesAndroidInjector(modules = [TransactionModule::class])
    abstract fun provideTransactionFragment(): TransactionFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [TransactionModule::class])
    abstract fun provideTransactionMyFragment(): TransactionMyFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [TransactionModule::class])
    abstract fun provideTransactionDetailActivity(): TransactionDetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun provideSearchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun provideAccountFragment(): AccountFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun provideAccountEditActivity(): AccountEditActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun provideAccountEditProfileActivity(): AccountEditProfileActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun provideProductMyFragment(): ProductMyFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [AccountModule::class])
    abstract fun provideProductMyAddActivity(): ProductMyAddActivity
}