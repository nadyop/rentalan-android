package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.category.CategoryContract
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.category.CategoryListPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class CategoryModule {

  @ActivityScope @Binds abstract fun provideCategoryView(
      categoryFragment: CategoryFragment): CategoryContract.View

  @ActivityScope @Binds abstract fun provideCategoryPresenter(
      categoryListPresenter: CategoryListPresenter): CategoryContract.Presenter
}