package com.gdn.rentalan.ui.product.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailUiModel(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val pricePerDay: Int = 0,
        var stock: Int = 0,
        val downPayment: Int? = null,
        val lateCharge: Int? = null,
        val categoryName: String = "",
        val productImage: String? = "",
        val status: String= "",
        val ownerName: String = "",
        val ownerPhone: String = "",
        val ownerCity: String = ""
) : Parcelable