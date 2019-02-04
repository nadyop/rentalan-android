package com.gdn.rentalan.di.module

import android.app.Application
import com.gdn.rentalan.ApplicationBase
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val appBase: ApplicationBase, private val api: ApiInterface) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return appBase
    }

    @Provides
    @Singleton
    @PerApplication
    fun provideRetrofit(): ApiInterface {
        return api
    }
}