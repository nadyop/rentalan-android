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
	val ownerId: String = "",

	@field:SerializedName("startDate")
	val startDate: String = "",

	@field:SerializedName("status")
	val status: String = ""
)