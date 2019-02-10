package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Transaction(

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
	val status: String = ""
)