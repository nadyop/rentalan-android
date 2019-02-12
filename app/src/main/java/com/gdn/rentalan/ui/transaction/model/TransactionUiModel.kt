package com.gdn.rentalan.ui.transaction.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionUiModel(
        val id: String = "",
        val downPayment: Int? = null,
        val endDate: String = "",
        val lateCharge: Int = 0,
        val quantity: Int = 0,
        val startDate: String = "",
        val totalPayment: Int = 0,
        val status: String = "",
        val ownerPhoneNumber: String? = null,
        val description: String? = null,
        val ownerId: String? = null,
        val pricePerDay: Int = 0,
        val categoryName: String? = null,
        val productImage: String? = null,
        val ownerName: String? = null,
        val name: String? = null,
        val ownerCity: String? = null,
        val stock: Int = 0,
        val renterName: String = "",
        val renterPhone: String = "",
        val renterCity: String = ""
    ) : Parcelable