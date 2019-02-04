package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class VerificationEmailRequest(

	@field:SerializedName("email")
	val email: String? = null
)