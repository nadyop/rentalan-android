package com.gdn.rentalan.ui.account.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AccountUiModel(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val sureName: String = "",
    val nik: String = "",
    val gender: String = "",
    val birthDate: String = "",
    val address: String = "",
    val city: String = "",
    val province: String = "",
    val ktpPhotoPath: String = "",
    val selfPhotoPath: String = "",
    val status: String = ""
) : Parcelable