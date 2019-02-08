package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.register.email.RegisterActivity
import com.gdn.rentalan.ui.register.email.RegisterContract
import com.gdn.rentalan.ui.register.email.RegisterPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class RegisterModule {

  @ActivityScope @Binds abstract fun provideRegisterView(
      registerActivity: RegisterActivity): RegisterContract.View

  @ActivityScope @Binds abstract fun provideRegisterPresenter(
      registerPresenter: RegisterPresenter): RegisterContract.Presenter
}