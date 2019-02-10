package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Product(

    @field:SerializedName("productImage") var productImage: String? = null,

    @field:SerializedName("downPayment") val downPayment: Int? = null,

    @field:SerializedName("name") val name: String? = null,

    @field:SerializedName("description") val description: String? = null,

    @field:SerializedName("lateCharge") val lateCharge: Int? = null,

    @field:SerializedName("id") val id: String? = null,

    @field:SerializedName("stock") val stock: Int = 0,

    @field:SerializedName("ownerId") val ownerId: String? = null,

    @field:SerializedName("ownerName") val ownerName: String? = null,

    @field:SerializedName("ownerCity") val ownerCity: String? = null,

    @field:SerializedName("ownerPhoneNumber") val ownerPhoneNumber: String? = null,

    @field:SerializedName("pricePerDay") val pricePerDay: Int = 0,

    @field:SerializedName("categoryName") val categoryName: String? = null,

    @field:SerializedName("status") val status: String? = null

)