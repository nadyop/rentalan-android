package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class CategoryRequest(

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("description")
        val description: String? = null
)