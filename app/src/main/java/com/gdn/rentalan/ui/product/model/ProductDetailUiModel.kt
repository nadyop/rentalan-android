package com.gdn.rentalan.ui.product.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailUiModel(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val pricePerDay: Int? = null,
        val stock: Int? = null,
        val downPayment: Int? = null,
        val lateCharge: Int? = null,
        val categoryName: String = "",
        val productImage: List<String>? = listOf()
) : Parcelable