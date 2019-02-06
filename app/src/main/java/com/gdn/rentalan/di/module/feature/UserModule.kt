package com.gdn.rentalan.di.module.feature

import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.user.UserContract
import com.gdn.rentalan.ui.user.UserFragment
import com.gdn.rentalan.ui.user.UserListPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @ActivityScope
    @Binds
    abstract fun provideUserView(
            userFragment: UserFragment): UserContract.View

    @ActivityScope
    @Binds
    abstract fun provideUserPresenter(
            userListPresenter: UserListPresenter): UserContract.Presenter

}