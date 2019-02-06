package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Product(

	@field:SerializedName("productImages")
	val productImages: List<String>? = null,

	@field:SerializedName("downPayment")
	val downPayment: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("lateCharge")
	val lateCharge: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("ownerId")
	val ownerId: String? = null,

	@field:SerializedName("pricePerDay")
	val pricePerDay: Int? = null,

	@field:SerializedName("categoryName")
	val categoryName: String? = null
)