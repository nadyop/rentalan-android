package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.search.SearchActivity
import com.gdn.rentalan.ui.search.SearchContract
import com.gdn.rentalan.ui.search.SearchPresenter
import dagger.Binds
import dagger.Module

@Module abstract class SearchModule {

  @ActivityScope @Binds abstract fun provideSearchView(searchActivity: SearchActivity): SearchContract.View

  @ActivityScope @Binds abstract fun provideSearchPresenter(
      searchPresenter: SearchPresenter): SearchContract.Presenter
}