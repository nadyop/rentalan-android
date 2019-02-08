package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.product.ProductActivityCheckout
import com.gdn.rentalan.ui.product.ProductActivityCheckoutContract
import com.gdn.rentalan.ui.product.ProductActivityCheckoutPresenter
import com.gdn.rentalan.ui.product.admin.*
import dagger.Binds
import dagger.Module

@Module
abstract class ProductModule {

    @ActivityScope
    @Binds
    abstract fun provideProductView(
            productFragment: ProductFragment): ProductContract.View

    @ActivityScope
    @Binds
    abstract fun provideProductPresenter(
            productListPresenter: ProductListPresenter): ProductContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideProductDetailView(
            productDetailActivity: ProductDetailActivity): ProductDetailContract.View

    @ActivityScope
    @Binds
    abstract fun provideProductDetailPresenter(
            productDetailPresenter: ProductDetailPresenter): ProductDetailContract.Presenter

    @ActivityScope
    @Binds
    abstract fun provideProductCheckoutView(
        productActivityCheckout: ProductActivityCheckout): ProductActivityCheckoutContract.View

    @ActivityScope
    @Binds
    abstract fun provideProductCheckoutPresenter(
      productActivityCheckoutPresenter: ProductActivityCheckoutPresenter): ProductActivityCheckoutContract.Presenter
}