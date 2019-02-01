package com.gdn.rentalan.di.module

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.category.CategoryContract
import com.gdn.rentalan.ui.category.CategoryListPresenter
import com.gdn.rentalan.ui.user.UserContract
import com.gdn.rentalan.ui.user.UserListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideCategoryListPresenter(): CategoryContract.Presenter {
        return CategoryListPresenter()
    }

    @Provides
    fun provideUserListPresenter(): UserContract.Presenter {
        return UserListPresenter()
    }

    @Provides
    fun provideApiService(): ApiInterface {
        return ApiInterface.create()
    }
}