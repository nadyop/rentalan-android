package com.gdn.rentalan.di.module

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.about.AboutContract
import com.gdn.rentalan.ui.about.AboutPresenter
import com.gdn.rentalan.ui.category.CategoryContract
import com.gdn.rentalan.ui.category.CategoryListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }

    @Provides
    fun provideCategoryListPresenter(): CategoryContract.Presenter {
        return CategoryListPresenter()
    }

//    @Provides
//    fun provideListPresenter(): ListContract.Presenter {
//        return ListPresenter()
//    }

    @Provides
    fun provideApiService(): ApiInterface {
        return ApiInterface.create()
    }
}