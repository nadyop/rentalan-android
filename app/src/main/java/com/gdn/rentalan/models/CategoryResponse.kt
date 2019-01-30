package com.gdn.rentalan.models

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<Category>? = null
)