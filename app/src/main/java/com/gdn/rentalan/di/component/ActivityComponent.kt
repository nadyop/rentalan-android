package com.gdn.rentalan.di.component

import com.gdn.rentalan.di.module.ActivityModule
import com.gdn.rentalan.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}