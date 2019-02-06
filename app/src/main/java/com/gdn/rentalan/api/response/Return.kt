package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Return(

	@field:SerializedName("lateCharge")
	val lateCharge: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)