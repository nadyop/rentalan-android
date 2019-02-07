package com.gdn.rentalan.ui.product

import com.gdn.rentalan.api.response.Product
import com.gdn.rentalan.ui.product.model.ProductDetailUiModel

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
                    item.categoryName,
                    item.productImages
            )
        }
    }
}