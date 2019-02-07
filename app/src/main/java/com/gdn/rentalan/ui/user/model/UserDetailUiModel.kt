package com.gdn.rentalan.ui.user.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetailUiModel(
    val address: String? = null,
    val gender: String? = null,
    val city: String? = null,
    val facePhotoPath: String? = null,
    val birthDate: String? = null,
    val nik: String? = null,
    val phoneNumber: String? = null,
    val province: String? = null,
    val ktpPhotoPath: String? = null,
    val id: String? = null,
    val sureName: String? = null,
    val email: String? = null,
    val selfPhotoPath: String? = null,
    val username: String? = null,
    val status: String? = null

) : Parcelable