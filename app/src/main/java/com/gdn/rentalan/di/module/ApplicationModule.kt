package com.gdn.rentalan.di.module

import android.app.Application
import com.gdn.rentalan.ApplicationBase
import com.gdn.rentalan.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [WebServiceModule::class])
class ApplicationModule(private val appBase: ApplicationBase) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return appBase
    }
}