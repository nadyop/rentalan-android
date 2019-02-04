package com.gdn.rentalan.di.module

import android.app.Activity
import com.gdn.rentalan.ui.category.AddCategoryContract
import com.gdn.rentalan.ui.category.AddCategoryPresenter
import com.gdn.rentalan.ui.main.MainContract
import com.gdn.rentalan.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun provideAddCategoryPresenter(): AddCategoryContract.Presenter {
        return AddCategoryPresenter()
    }

}