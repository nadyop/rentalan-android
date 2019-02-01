package com.gdn.rentalan.di.component

import com.gdn.rentalan.di.module.FragmentModule
import com.gdn.rentalan.ui.category.CategoryFragment
import com.gdn.rentalan.ui.user.UserFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(listFragment: CategoryFragment)

    fun inject(listFragment: UserFragment)
}