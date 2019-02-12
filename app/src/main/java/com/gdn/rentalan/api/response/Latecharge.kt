package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class Latecharge(

        @field:SerializedName("lateCharge")
        var lateCharge: String? = null,

        @field:SerializedName("id")
        var id: String? = null
)
