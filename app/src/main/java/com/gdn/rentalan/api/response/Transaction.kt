package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Transaction(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("totalPayment")
    val totalPayment: Int? = null,

	@field:SerializedName("renterId")
	val renterId: String? = null,

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("downPayment")
	val downPayment: Int? = null,

	@field:SerializedName("lateCharge")
	val lateCharge: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ownerId")
	val ownerId: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)