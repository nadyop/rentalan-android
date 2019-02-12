package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class TransactionDetail(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("quantity")
	val quantity: Int = 0,

	@field:SerializedName("totalPayment")
	val totalPayment: Int = 0,

	@field:SerializedName("renterId")
	val renterId: String = "",

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("downPayment")
	val downPayment: Int? = null,

	@field:SerializedName("lateCharge")
	val lateCharge: Int = 0,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ownerId")
	val ownerId: String? = null,

	@field:SerializedName("ownerPhoneNumber")
	val ownerPhoneNumber: String = "",

	@field:SerializedName("startDate")
	val startDate: String = "",

	@field:SerializedName("description")
	val description: String = "",

	@field:SerializedName("pricePerDay")
	val pricePerDay: Int = 0,

	@field:SerializedName("categoryName")
	val categoryName: String = "",

	@field:SerializedName("productImage")
	val productImage: String = "",

	@field:SerializedName("ownerName")
	val ownerName: String = "",

	@field:SerializedName("name")
	val name: String = "",

	@field:SerializedName("ownerCity")
	val ownerCity: String = "",

	@field:SerializedName("stock")
	val stock: Int = 0,

	@field:SerializedName("status")
	val status: String = ""
)