package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class ProductRequest(

	@field:SerializedName("productImagesPath")
	val productImagesPath: List<String?>? = null,

	@field:SerializedName("downPayment")
	val downPayment: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("lateCharge")
	val lateCharge: Int? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("pricePerDay")
	val pricePerDay: Int? = null,

	@field:SerializedName("categoryId")
	val categoryId: String? = null
)