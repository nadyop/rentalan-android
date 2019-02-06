package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.category.*
import dagger.Binds
import dagger.Module

@Module
abstract class CategoryModule {

  @ActivityScope @Binds abstract fun provideCategoryView(
      categoryFragment: CategoryFragment): CategoryContract.View

  @ActivityScope @Binds abstract fun provideCategoryPresenter(
      categoryListPresenter: CategoryListPresenter): CategoryContract.Presenter

  @ActivityScope @Binds abstract fun provideCategoryAddView(
          categoryAddActivity: CategoryAddActivity): CategoryAddContract.View

  @ActivityScope @Binds abstract fun provideCategoryAddPresenter(
          categoryAddPresenter: CategoryAddPresenter): CategoryAddContract.Presenter
}