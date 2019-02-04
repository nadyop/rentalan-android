package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("role")
	val role: Any? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)