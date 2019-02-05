package com.gdn.rentalan

import android.app.Activity
import android.app.Application
import android.app.Service
import com.gdn.rentalan.di.component.ApplicationComponent
import com.gdn.rentalan.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class ApplicationBase : Application(), HasActivityInjector, HasServiceInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    lateinit var appComponent: ApplicationComponent

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }

    fun getInstance(): ApplicationBase {
        return instance
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        DaggerApplicationComponent.create().inject(this)
//        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    companion object {
        lateinit var instance: ApplicationBase private set
    }

    fun getApplicationComponent(): ApplicationComponent {
        return this.appComponent
    }

}
