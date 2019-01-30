package com.gdn.rentalan.di.component

import com.gdn.rentalan.ApplicationBase
import com.gdn.rentalan.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: ApplicationBase)

}