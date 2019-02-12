package com.gdn.rentalan.ui.transaction.model

import com.gdn.rentalan.api.response.Transaction

interface TransactionMapper {
    companion object {
        fun mapToTransactionUiModel(item: Transaction): TransactionUiModel {
            return TransactionUiModel(
                    id = item.id.orEmpty(),
                    downPayment = item.downPayment,
                    endDate = item.endDate,
                    lateCharge = item.lateCharge,
                    quantity = item.quantity,
                    startDate = item.startDate,
                    totalPayment = item.totalPayment,
                    status = item.status,
                    name = item.product?.name
            )
        }
    }
}