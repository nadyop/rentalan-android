package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class UserUpdateRequest(

        @field:SerializedName("phoneNumber")
        val phoneNumber: String = "",

        @field:SerializedName("sureName")
        val sureName: String = "",

        @field:SerializedName("nik")
        val nik: String = "",

        @field:SerializedName("gender")
        val gender: String = "",

        @field:SerializedName("birthDate")
        val birthDate: String = "",

        @field:SerializedName("address")
        val address: String = "",

        @field:SerializedName("city")
        val city: String = "",

        @field:SerializedName("province")
        val province: String = ""
)