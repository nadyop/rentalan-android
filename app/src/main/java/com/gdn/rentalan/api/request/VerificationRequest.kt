package com.gdn.rentalan.api.request

import com.google.gson.annotations.SerializedName

data class VerificationRequest(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("ktpPhotoPath")
	val ktpPhotoPath: String? = null,

	@field:SerializedName("facePhotoPath")
	val facePhotoPath: String? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("selfPhotoPath")
	val selfPhotoPath: String? = null
)