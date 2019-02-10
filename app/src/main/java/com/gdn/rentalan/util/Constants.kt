package com.gdn.rentalan.util

import java.text.NumberFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://rentalan-restapi.herokuapp.com/rentalan-api/"
        const val URL_PRODUCT = "https://rentalan-restapi.herokuapp.com/rentalan-api/image?type=product&id="
        const val URL_KTP = "https://rentalan-restapi.herokuapp.com/rentalan-api/image?type=ktp&id="
        const val URL_SELF = "https://rentalan-restapi.herokuapp.com/rentalan-api/image?type=self&id="

        private var localeID = Locale("in", "ID")
        var formatRupiah = NumberFormat.getCurrencyInstance(localeID)!!
    }
}