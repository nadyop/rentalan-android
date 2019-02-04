package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<Transaction>? = null
)