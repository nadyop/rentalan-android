package com.gdn.rentalan.api.request

data class LoginRequest(
	val password: String? = null,
	val username: String? = null
)
