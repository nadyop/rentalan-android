package com.gdn.rentalan.di.component

import com.gdn.rentalan.di.module.FragmentModule
import com.gdn.rentalan.ui.category.CategoryFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(listFragment: CategoryFragment)
}