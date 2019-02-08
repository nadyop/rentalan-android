package com.gdn.rentalan.ui.transaction.model

import com.gdn.rentalan.api.response.Transaction

interface TransactionMapper {
    companion object {
        fun mapToTransactionUiModel(item: Transaction): TransactionUiModel {
            return TransactionUiModel(

                    item.id.orEmpty(),
                    item.downPayment,
                    item.endDate.orEmpty(),
                    item.lateCharge,
                    item.quantity,
                    item.startDate,
                    item.totalPayment,
                    item.status,
                    item.productId
            )
        }
    }
}