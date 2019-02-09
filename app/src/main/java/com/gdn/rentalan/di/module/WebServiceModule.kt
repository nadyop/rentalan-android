package com.gdn.rentalan.di.module

import android.app.Application
import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.util.Constants
import com.gdn.rentalan.util.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebServiceModule {

  private fun buildRetrofit(): Retrofit {
    return retrofit2.Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()
  }

  @Provides
  internal fun provideRetrofit(): Retrofit {
    return buildRetrofit()
  }

  @Provides
  internal fun provideApiInterface(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
  }

//  @Provides
//  internal fun provideLoginRepository(): LoginRepository {
//    return LoginRepository()
//  }
}