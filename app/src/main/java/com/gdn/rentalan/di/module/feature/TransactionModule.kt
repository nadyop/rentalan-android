package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.transaction.*
import dagger.Binds
import dagger.Module

@Module
abstract class TransactionModule {

    @ActivityScope
    @Binds
    abstract fun provideTransactionView(
            transactionFragment: TransactionFragment): TransactionContract.View

    @ActivityScope
    @Binds
    abstract fun provideTransactionPresenter(
            transactionPresenter: TransactionPresenter): TransactionContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideMyTransactionView(
            transactionFragment: TransactionMyFragment): TransactionMyContract.View

    @ActivityScope
    @Binds
    abstract fun provideMyTransactionPresenter(
            transactionPresenter: TransactionMyPresenter): TransactionMyContract.Presenter
}