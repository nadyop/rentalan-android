package com.gdn.rentalan.api

import com.gdn.rentalan.api.response.CategoryResponse
import com.gdn.rentalan.api.response.UserResponse
import com.gdn.rentalan.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    //    category
    @GET("category")
    fun getCategoryList(): Observable<CategoryResponse>

    //    user
    @GET("user?status=all")
    fun getUserList(): Observable<UserResponse>

    //    product
//    @GET("product?status=active")
//    fun getProductList(): Observable<ProductResponse>

    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}