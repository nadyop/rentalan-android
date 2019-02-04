package com.gdn.rentalan.api

import com.gdn.rentalan.api.request.*
import com.gdn.rentalan.api.response.*
import com.gdn.rentalan.util.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    //    category
    @GET("category")
    fun getCategoryList(): Observable<CategoryResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("category")
    fun getCategoryDetail(@Query("categoryId") categoryId: String): Call<CategoryResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("category")
    fun addCategory(@Body categoryRequest: CategoryRequest): Call<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("category")
    fun updateCategoryDetail(@Query("categoryId") categoryId: String): Call<RestCommonResponse>

    //    product
    @GET("product?status=waiting")
    fun getProductListWaiting(): Observable<ProductResponse>

    @GET("product?status=active")
    fun getProductListActive(): Observable<ProductResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product")
    fun addProduct(@Query("ownerId") ownerId: String, @Body productRequest: ProductRequest): Call<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product")
    fun updateProduct(@Query("ownerId") ownerId: String, @Query("productId") productId: String, @Body productRequest: ProductRequest): Call<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product/verification")
    fun verifProduct(@Query("productId") productId: String): Call<RestCommonResponse>

    @GET("product")
    fun getProductListOwnerId(@Query("ownerId") ownerId: String): Observable<ProductResponse>

    @GET("product")
    fun getProductDetail(@Query("productId") productId: String): Observable<ProductResponse>

    @GET("product")
    fun searchProduct(@Query("provinceCode") provinceCode: String, @Query("cityCode") cityCode: String): Observable<ProductResponse>

    //    rent
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("rent")
    fun rentProduct(@Query("userId") userId: String, @Body rentRequest: RentRequest): Call<RestCommonResponse>

    @GET("rent/transaction")
    fun getTransactionList(@Query("userId") userId: String): Observable<TransactionResponse>

    @POST("rent/accept")
    fun acceptRentProduct(@Query("transactionId") transactionId: String): Call<RestCommonResponse>

    @POST("rent/return")
    fun returnRentProduct(@Query("transactionId") transactionId: String): Call<RestCommonResponse>

    //    user
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/register")
    fun userRegister(@Body registerRequest: RegisterRequest): Call<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/verification")
    fun verifUser(@Query("userId") userId: String, @Body userRequest: UserRequest): Call<RestCommonResponse>

    @GET("user")
    fun getUserDetail(@Query("userId") userId: String): Observable<UserResponse>

    @GET("user?status=all")
    fun getUserList(): Observable<UserResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user")
    fun updateUser(@Query("userId") userId: String, @Body userRequest: UserRequest): Call<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/change-password")
    fun changePassword(@Query("userId") userId: String, @Body passwordRequest: PasswordRequest): Call<RestCommonResponse>

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