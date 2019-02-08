package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class RegisterEmailRequest(

	@field:SerializedName("email")
	val email: String? = null
)