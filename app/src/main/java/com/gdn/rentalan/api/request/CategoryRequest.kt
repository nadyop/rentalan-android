package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class CategoryRequest(

        @field:SerializedName("name")
        var name: String? = null,

        @field:SerializedName("description")
        var description: String? = null
)