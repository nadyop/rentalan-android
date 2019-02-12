package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Transaction(

        @field:SerializedName("product")
        val product: Product? = null,

        @field:SerializedName("quantity")
        val quantity: Int = 0,

        @field:SerializedName("totalPayment")
        val totalPayment: Int = 0,

        @field:SerializedName("renterId")
        val renterId: String? = null,

        @field:SerializedName("productId")
        val productId: String = "",

        @field:SerializedName("endDate")
        val endDate: String = "",

        @field:SerializedName("downPayment")
        val downPayment: Int? = null,

        @field:SerializedName("lateCharge")
        val lateCharge: Int = 0,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("ownerId")
        val ownerId: String? = null,

        @field:SerializedName("startDate")
        val startDate: String = "",

        @field:SerializedName("status")
        val status: String = "",

        @field:SerializedName("ownerPhoneNumber")
        val ownerPhoneNumber: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("pricePerDay")
        val pricePerDay: Int? = null,

        @field:SerializedName("categoryName")
        val categoryName: String? = null,

        @field:SerializedName("productImage")
        val productImage: String? = null,

        @field:SerializedName("ownerName")
        val ownerName: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("ownerCity")
        val ownerCity: String? = null,

        @field:SerializedName("stock")
        val stock: Int? = null
)