package com.gdn.rentalan.di.component

import com.gdn.rentalan.di.module.FragmentModule
import com.gdn.rentalan.ui.about.AboutFragment
import com.gdn.rentalan.ui.list.ListFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(aboutFragment: AboutFragment)

    fun inject(listFragment: ListFragment)

}