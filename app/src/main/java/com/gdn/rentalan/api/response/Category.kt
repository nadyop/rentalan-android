package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Category(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)