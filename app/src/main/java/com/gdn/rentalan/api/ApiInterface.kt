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
    @GET("product?status=waiting")
    fun getProductListWaiting(): Observable<RestListResponse<Product>>

    @GET("product?status=active")
    fun getProductListActive(): Observable<RestListResponse<Product>>

//    @GET("product?ownerId=c54e79b6-2a69-437b-a9f0-67f153ede9b1")
    @GET("product")
    fun getTransactionDetail(@Query("ownerId")id: String = "c54e79b6-2a69-437b-a9f0-67f153ede9b1"):
    Observable<RestSingleResponse<Transaction>>

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

    @GET("rent/transaction?userId=c54e79b6-2a69-437b-a9f0-67f153ede9b1")
    fun getTransactionListOwner(@Query("isOwner") isOwner: Boolean = true)
//    fun getTransactionListOwner(@Query("userId") userId: String = "c54e79b6-2a69-437b-a9f0-67f153ede9b1", @Query("isOwner") isOwner: Boolean = true)
            : Observable<RestListResponse<Transaction>>

    @GET("rent/transaction")
    fun getTransactionListRenter(@Query("userId") userId: String = "c54e79b6-2a69-437b-a9f0-67f153ede9b1",
                                @Query("isOwner") isOwner: Boolean = false)
            : Observable<RestListResponse<Transaction>>

    @POST("rent/accept")
    fun acceptRentProduct(@Query("transactionId") transactionId: String): Observable<RestCommonResponse>

    @POST("rent/return")
    fun returnRentProduct(@Query("transactionId") transactionId: String): Observable<RestCommonResponse>

    //    user
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/register")
    fun registerProfile(@Body registerRequest: RegisterRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/verification")
    fun verifUser(@Query("userId") userId: String): Observable<RestCommonResponse>

    @POST("user/verification")
    fun registerEmail(@Body registerEmailRequest: RegisterEmailRequest): Observable<RegisterEmailResponse>

    @GET("user")
    fun getUserDetail(@Query("userId") userId: String): Observable<RestSingleResponse<User>>

    @GET("user?status=all")
    fun getUserList(): Observable<RestListResponse<User>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user")
    fun updateUser(@Query("userId") userId: String, @Body userRequest: UserRequest): Observable<RestCommonResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/login")
    fun login(@Body loginRequest: LoginRequest): Observable<RestSingleResponse<Login>>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("user/change-password")
    fun changePassword(@Query("userId") userId: String, @Body passwordRequest: PasswordRequest): Observable<RestCommonResponse>
}