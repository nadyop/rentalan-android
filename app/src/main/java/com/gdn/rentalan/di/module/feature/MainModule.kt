package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.main.admin.MainActivity
import com.gdn.rentalan.ui.main.admin.MainContract
import com.gdn.rentalan.ui.main.admin.MainPresenter
import com.gdn.rentalan.ui.main.user.UserMainActivity
import com.gdn.rentalan.ui.main.user.UserMainContract
import com.gdn.rentalan.ui.main.user.UserMainPresenter
import dagger.Binds
import dagger.Module

@Module abstract class MainModule {

  @Binds @ActivityScope
  internal abstract fun provideMainView(mainView: MainActivity): MainContract.View

  @Binds @ActivityScope
  internal abstract fun provideMainPresenter(
      mainPresenter: MainPresenter): MainContract.Presenter

  @Binds @ActivityScope
  internal abstract fun provideUserMainView(mainView: UserMainActivity): UserMainContract.View

  @Binds @ActivityScope
  internal abstract fun provideUserMainPresenter(
      mainPresenter: UserMainPresenter): UserMainContract.Presenter
}