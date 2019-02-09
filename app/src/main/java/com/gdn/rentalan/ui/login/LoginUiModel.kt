package com.gdn.rentalan.ui.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginUiModel(
    val userID: String = "",
    val role: String? = null
) : Parcelable