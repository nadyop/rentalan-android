package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class PasswordRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("newPassword")
	val newPassword: String? = null
)