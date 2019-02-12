package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.transaction.detail.TransactionDetailActivity
import com.gdn.rentalan.ui.transaction.detail.TransactionDetailContract
import com.gdn.rentalan.ui.transaction.detail.TransactionDetailPresenter
import com.gdn.rentalan.ui.transaction.owner.TransactionMyContract
import com.gdn.rentalan.ui.transaction.owner.TransactionMyFragment
import com.gdn.rentalan.ui.transaction.owner.TransactionMyPresenter
import com.gdn.rentalan.ui.transaction.renter.TransactionContract
import com.gdn.rentalan.ui.transaction.renter.TransactionFragment
import com.gdn.rentalan.ui.transaction.renter.TransactionPresenter
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

    @ActivityScope
    @Binds
    abstract fun provideTransactionDetailView(
        transactionDetailActivity: TransactionDetailActivity): TransactionDetailContract.View

    @ActivityScope
    @Binds
    abstract fun provideTransactionDetailPresenter(
        transactionDetailPresenter: TransactionDetailPresenter): TransactionDetailContract.Presenter
}