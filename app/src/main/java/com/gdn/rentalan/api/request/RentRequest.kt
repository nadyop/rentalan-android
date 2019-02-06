package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class RentRequest(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
)