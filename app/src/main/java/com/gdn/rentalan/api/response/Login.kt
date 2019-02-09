package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("success")
	val success: String = "",

	@field:SerializedName("userId")
	val userId: String = ""
)