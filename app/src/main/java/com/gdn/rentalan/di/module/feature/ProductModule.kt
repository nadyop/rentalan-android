package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.product.ProductContract
import com.gdn.rentalan.ui.product.ProductFragment
import com.gdn.rentalan.ui.product.ProductListPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ProductModule {

    @ActivityScope
    @Binds
    abstract fun provideUserView(
            productFragment: ProductFragment): ProductContract.View

    @ActivityScope
    @Binds
    abstract fun provideUserPresenter(
            productListPresenter: ProductListPresenter): ProductContract.Presenter

}