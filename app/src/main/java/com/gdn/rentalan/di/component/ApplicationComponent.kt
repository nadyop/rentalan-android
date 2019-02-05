package com.gdn.rentalan.di.component

import com.gdn.rentalan.ApplicationBase
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.di.module.ActivityModule
import com.gdn.rentalan.di.module.ApplicationModule
import com.gdn.rentalan.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityModule::class])
interface ApplicationComponent : AndroidInjector<ApplicationBase>{

    fun apiInterface(): ApiInterface
}