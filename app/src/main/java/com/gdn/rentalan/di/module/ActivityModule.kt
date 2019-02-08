package com.gdn.rentalan.di.module

import com.gdn.rentalan.di.module.feature.*
import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.category.CategoryAddActivity
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.dashboard.DashboardFragment
import com.gdn.rentalan.ui.login.LoginActivity
import com.gdn.rentalan.ui.main.MainActivity
import com.gdn.rentalan.ui.product.ProductActivityCheckout
import com.gdn.rentalan.ui.product.admin.ProductDetailActivity
import com.gdn.rentalan.ui.product.admin.ProductFragment
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
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

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
}