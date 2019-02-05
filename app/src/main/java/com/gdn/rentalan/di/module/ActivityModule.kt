package com.gdn.rentalan.di.module

import com.gdn.rentalan.di.module.feature.CategoryModule
import com.gdn.rentalan.di.module.feature.MainModule
import com.gdn.rentalan.di.scope.ActivityScope
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [CategoryModule::class])
    abstract fun provideCategoryFragment(): CategoryFragment

}