package com.gdn.rentalan.api

import com.google.gson.annotations.SerializedName

open class RestCommonResponse(

		@field:SerializedName("code") val code: Int = 200
)