package com.gdn.rentalan.ui.product.model

import com.gdn.rentalan.api.response.Product

interface ProductMapper {
    companion object {
        fun mapToProductDetailUiModel(item: Product): ProductDetailUiModel {
            return ProductDetailUiModel(
                    item.id.orEmpty(),
                    item.name.orEmpty(),
                    item.description.orEmpty(),
                    item.pricePerDay,
                    item.stock,
                    item.downPayment,
                    item.lateCharge,
                    item.categoryName.toString(),
                    item.productImage,
                    item.ownerName.toString(),
                    item.ownerPhoneNumber.toString()
            )
        }
    }
}