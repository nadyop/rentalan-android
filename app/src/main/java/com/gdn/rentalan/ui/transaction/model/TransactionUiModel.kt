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
        val productId: String = ""

) : Parcelable