package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Latecharge(
		@field:SerializedName("productImages") var lateCharge: String? = null,
		@field:SerializedName("productImages") var id: String? = null
)
