package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.account.product.ProductMyContract
import com.gdn.rentalan.ui.account.product.ProductMyFragment
import com.gdn.rentalan.ui.account.product.ProductMyPresenter
import com.gdn.rentalan.ui.account.product.add.ProductMyAddActivity
import com.gdn.rentalan.ui.account.product.add.ProductMyAddContract
import com.gdn.rentalan.ui.account.product.add.ProductMyAddPresenter
import com.gdn.rentalan.ui.account.profile.AccountContract
import com.gdn.rentalan.ui.account.profile.AccountFragment
import com.gdn.rentalan.ui.account.profile.AccountPresenter
import com.gdn.rentalan.ui.account.profile.edit.AccountEditActivity
import com.gdn.rentalan.ui.account.profile.edit.AccountEditContract
import com.gdn.rentalan.ui.account.profile.edit.AccountEditPresenter
import com.gdn.rentalan.ui.account.profile.editprofile.AccountEditProfileActivity
import com.gdn.rentalan.ui.account.profile.editprofile.AccountEditProfileContract
import com.gdn.rentalan.ui.account.profile.editprofile.AccountEditProfilePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class AccountModule {

    @ActivityScope
    @Binds
    abstract fun provideAccountView(
            accountFragment: AccountFragment): AccountContract.View

    @ActivityScope
    @Binds
    abstract fun provideAccountPresenter(
            accountPresenter: AccountPresenter): AccountContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideAccountEditView(
            accountEditActivity: AccountEditActivity): AccountEditContract.View

    @ActivityScope
    @Binds
    abstract fun provideAccountEditPresenter(
            accountEditPresenter: AccountEditPresenter): AccountEditContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideAccountEditProfileView(
            accountEditProfileActivity: AccountEditProfileActivity): AccountEditProfileContract.View

    @ActivityScope
    @Binds
    abstract fun provideAccountEditProfilePresenter(
            accountEditProfilePresenter: AccountEditProfilePresenter): AccountEditProfileContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideProductMyView(
            productMyFragment: ProductMyFragment): ProductMyContract.View

    @ActivityScope
    @Binds
    abstract fun provideProductMyPresenter(
            productMyPresenter: ProductMyPresenter): ProductMyContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideProductMyAddView(
            productMyAddActivity: ProductMyAddActivity): ProductMyAddContract.View

    @ActivityScope
    @Binds
    abstract fun provideProductMyAddPresenter(
            productMyAddPresenter: ProductMyAddPresenter): ProductMyAddContract.Presenter
}