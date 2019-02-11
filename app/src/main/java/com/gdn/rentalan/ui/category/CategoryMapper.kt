package com.gdn.rentalan.ui.category

import com.gdn.rentalan.api.response.Category

interface CategoryMapper {
    companion object {
        fun mapToCategoryUiModel(item: Category): CategoryUiModel {
            return CategoryUiModel(
                    item.id.orEmpty(),
                    item.name.orEmpty()
            )
        }
    }
}