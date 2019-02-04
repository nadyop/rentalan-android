package com.gdn.rentalan

import android.app.Application
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.di.component.ApplicationComponent
import com.gdn.rentalan.di.component.DaggerApplicationComponent

class ApplicationBase : Application() {

    lateinit var component: ApplicationComponent
    lateinit var api: ApiInterface

    override fun onCreate() {
        super.onCreate()

        instance = this
        DaggerApplicationComponent.create().inject(this)
        getApi()
    }

    private fun getApi() {
        api = ApiInterface.create()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: ApplicationBase private set
    }
}
