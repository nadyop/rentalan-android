package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.dashboard.DashboardContract
import com.gdn.rentalan.ui.dashboard.DashboardFragment
import com.gdn.rentalan.ui.dashboard.DashboardPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardModule {

  @ActivityScope
  @Binds
  abstract fun provideUserView(
      dashboardFragment: DashboardFragment): DashboardContract.View

  @ActivityScope
  @Binds
  abstract fun provideUserPresenter(
      dashboardPresenter: DashboardPresenter): DashboardContract.Presenter
}