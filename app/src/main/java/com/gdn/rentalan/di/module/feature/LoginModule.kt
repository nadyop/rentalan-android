package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.login.LoginActivity
import com.gdn.rentalan.ui.login.LoginContract
import com.gdn.rentalan.ui.login.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {

  @ActivityScope @Binds abstract fun provideLoginView(
      loginActivity: LoginActivity): LoginContract.View

  @ActivityScope @Binds abstract fun provideLoginPresenter(
      loginPresenter: LoginPresenter): LoginContract.Presenter
}