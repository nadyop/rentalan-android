package com.gdn.rentalan.api

import com.gdn.rentalan.api.request.*
import com.gdn.rentalan.api.response.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    //    category
    @GET("category")
    fun getCategoryList(): Observable<RestListResponse<Category>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("category")
    fun getCategoryDetail(@Query("categoryId") categoryId: String): Observable<Category>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("category")
    fun addCategory(@Body categoryRequest: CategoryRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("category")
    fun updateCategoryDetail(@Query("categoryId") categoryId: String): Observable<RestCommonResponse>

    //    product
    @GET("product?status=all")
    fun getProductListWaiting(): Observable<RestListResponse<Product>>

    @GET("product?status=active")
    fun getProductListActive(): Observable<RestListResponse<Product>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product")
    fun addProduct(@Query("ownerId") ownerId: String, @Body productRequest: ProductRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product")
    fun updateProduct(@Query("ownerId") ownerId: String, @Query("productId") productId: String, @Body productRequest: ProductRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("product/verification")
    fun verifProduct(@Query("productId") productId: String, @Query("accept") accept: String): Observable<RestCommonResponse>

    @GET("product")
    fun getProductListOwnerId(@Query("ownerId") ownerId: String): Observable<RestListResponse<Product>>

    @GET("product")
    fun getProductDetail(@Query("productId") id: String): Observable<RestSingleResponse<Product>>

    @GET("product")
    fun searchProduct(@Query("provinceCode") provinceCode: String, @Query("cityCode") cityCode: String): Observable<RestListResponse<Product>>

    //    rent
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("rent")
    fun rentProduct(@Query("userId") userId: String, @Body rentRequest: RentRequest): Observable<RestCommonResponse>

    @GET("rent/transaction")
    fun getTransactionList(@Query("userId") userId: String): Observable<RestListResponse<Transaction>>

    @POST("rent/accept")
    fun acceptRentProduct(@Query("transactionId") transactionId: String): Observable<RestCommonResponse>

    @POST("rent/return")
    fun returnRentProduct(@Query("transactionId") transactionId: String): Observable<RestCommonResponse>

    //    user
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/register")
    fun userRegister(@Body registerRequest: RegisterRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/verification")
    fun verifUser(@Query("userId") userId: String, @Body userRequest: UserRequest): Observable<RestCommonResponse>

    @GET("user")
    fun getUserDetail(@Query("userId") userId: String): Observable<RestSingleResponse<User>>

    @GET("user?status=all")
    fun getUserList(): Observable<RestListResponse<User>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user")
    fun updateUser(@Query("userId") userId: String, @Body userRequest: UserRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Observable<RestListResponse<Login>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/change-password")
    fun changePassword(@Query("userId") userId: String, @Body passwordRequest: PasswordRequest): Observable<RestCommonResponse>
}