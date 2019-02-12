package com.gdn.rentalan.api.response

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("city")
	val city: String = "",

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String = "",

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("ktpPhotoPath")
	val ktpPhotoPath: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("sureName")
	val sureName: String = "",

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("selfPhotoPath")
	val selfPhotoPath: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)