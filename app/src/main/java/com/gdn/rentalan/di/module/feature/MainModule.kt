package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.main.MainActivity
import com.gdn.rentalan.ui.main.MainContract
import com.gdn.rentalan.ui.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module abstract class MainModule {

  @Binds @ActivityScope
  internal abstract fun provideMainView(mainView: MainActivity): MainContract.View

  @Binds @ActivityScope
  internal abstract fun provideMainPresenter(
      mainPresenter: MainPresenter): MainContract.Presenter
}