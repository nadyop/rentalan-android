package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.register.email.RegisterEmailActivity
import com.gdn.rentalan.ui.register.email.RegisterEmailContract
import com.gdn.rentalan.ui.register.email.RegisterEmailPresenter
import com.gdn.rentalan.ui.register.profile.RegisterProfileActivity
import com.gdn.rentalan.ui.register.profile.RegisterProfileContract
import com.gdn.rentalan.ui.register.profile.RegisterProfilePresenter
import dagger.Binds
import dagger.Module

@Module
abstract class RegisterModule {

  @ActivityScope @Binds abstract fun provideRegisterView(
      registerEmailActivity: RegisterEmailActivity): RegisterEmailContract.View

  @ActivityScope @Binds abstract fun provideRegisterPresenter(
      registerEmailPresenter: RegisterEmailPresenter): RegisterEmailContract.Presenter

  @ActivityScope @Binds abstract fun provideRegisterProfileView(
      registerProfileActivity: RegisterProfileActivity): RegisterProfileContract.View

  @ActivityScope @Binds abstract fun provideRegisterProfilePresenter(
      registerProfilePresenter: RegisterProfilePresenter): RegisterProfileContract.Presenter
}