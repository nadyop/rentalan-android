package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<Product>? = null
)