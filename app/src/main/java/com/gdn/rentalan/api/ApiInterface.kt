package com.gdn.rentalan.api

import com.gdn.rentalan.api.request.*
import com.gdn.rentalan.api.response.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiInterface {

  //    category
  @GET("category") fun getCategoryList(): Observable<RestListResponse<Category>>

  @Headers("Accept: application/json", "Content-Type: application/json") @GET("category")
  fun getCategoryDetail(@Query("categoryId") categoryId: String): Observable<Category>

  @Headers("Accept: application/json", "Content-Type: application/json") @POST("category")
  fun addCategory(@Body categoryRequest: CategoryRequest): Observable<RestCommonResponse>

  @Headers("Accept: application/json", "Content-Type: application/json") @POST("category")
  fun updateCategoryDetail(@Query("categoryId") categoryId: String): Observable<RestCommonResponse>

  //    product
  @Headers("Accept: application/json", "Content-Type: multipart/form-data") @Multipart
  @POST("product") fun addProductByOwner(@Query("ownerId") ownerId: String, @Part("image")
  file: MultipartBody.Part, @Part("request")
  productRequest: ProductRequest): Observable<RestCommonResponse>

  @Headers("Accept: application/json", "Content-Type: multipart/form-data") @Multipart
  @PUT("product") fun updateProductByOwner(@Query("ownerId") ownerId: String, @Query("productId")
  productId: String, @Part("image") file: MultipartBody.Part, @Part("request")
  productRequest: ProductRequest): Observable<RestCommonResponse>

  @Headers("Accept: application/json", "Content-Type: application/json")
  @PUT("product/verification") fun verifProduct(@Query("productId") productId: String,
      @Query("accept") accept: String): Observable<RestCommonResponse>

  @GET("product") fun getAllProductByOwner(@Query("ownerId")
  ownerId: String): Observable<RestListResponse<Product>>

  @GET("product") fun getProductDetail(@Query("productId")
  productId: String): Observable<RestSingleResponse<Product>>

  @GET("product?status=waiting") fun getProductListWaiting(): Observable<RestListResponse<Product>>

  @GET("product?status=active") fun getProductListActive(): Observable<RestListResponse<Product>>

  @GET("product") fun searchProduct(@Query("provinceCode") provinceCode: String = "",
      @Query("cityCode") cityCode: String = "", @Query("searchKey")
      searchKey: String = ""): Observable<RestListResponse<Product>>

  @DELETE("product") fun deletProduct(@Query("ownerId") ownerId: String, @Query("productId")
  productId: String)

  //    rent
  @POST("rent") fun rentCheckout(@Query("userId") userId: String, @Body
  rentRequest: RentRequest): Observable<RestCommonResponse>

  @GET("rent/transaction") fun getTransactionListOwner(@Query("userId") userId: String,
      @Query("isOwner") isOwner: Boolean = true): Observable<RestListResponse<Transaction>>

  @GET("rent/transaction") fun getTransactionListRenter(@Query("userId") userId: String,
      @Query("isOwner") isOwner: Boolean = false): Observable<RestListResponse<Transaction>>

  @PUT("rent/accept") fun acceptRentProduct(@Query("transactionId") transactionId: String,
      @Query("isOwner") isOwner: Boolean = true): Observable<RestCommonResponse>

  @PUT("rent/return") fun returnRentProduct(@Query("transactionId")
  transactionId: String): Observable<RestSingleResponse<Latecharge>>

  //    user
  @POST("user/register") fun registerProfile(@Body
  registerRequest: RegisterRequest): Observable<RestCommonResponse>

  @PUT("user/verification") fun verifUser(@Query("userId")
  userId: String): Observable<RestCommonResponse>

  @Multipart @PUT("user/verification") fun verifByUser(@Query("userId") userId: String,
      @Part("request") userRequest: UserRequest, @Part("ktpImage") ktpImage: MultipartBody.Part,
      @Part("selfImage") selfImage: MultipartBody.Part ): Observable<RestCommonResponse>

  @POST("user/verification") fun registerEmail(@Body
  registerEmailRequest: RegisterEmailRequest): Observable<RegisterEmailResponse>

  @GET("user") fun getUserDetail(@Query("userId")
  userId: String): Observable<RestSingleResponse<User>>

  @GET("user?status=waiting") fun getUserListWaiting(): Observable<RestListResponse<User>>

  @PUT("user") fun updateUser(@Query("userId") userId: String, @Body
  userRequest: UserRequest): Observable<RestCommonResponse>

  @POST("user/login") fun login(@Body
  loginRequest: LoginRequest): Observable<RestSingleResponse<Login>>

  @PUT("user/change-password") fun changePassword(@Query("userId") userId: String, @Body
  passwordRequest: PasswordRequest): Observable<RestCommonResponse>

  //  image
  @GET("image") fun getImageProduct(@Query("type") type: String = "product", @Query("id")
  id: String): Observable<Image>

  //    @GET("product?ownerId=c54e79b6-2a69-437b-a9f0-67f153ede9b1")
  @GET("product") fun getTransactionDetail(@Query("ownerId")
  id: String = "c54e79b6-2a69-437b-a9f0-67f153ede9b1"): Observable<RestSingleResponse<Transaction>>

}