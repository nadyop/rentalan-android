package com.gdn.rentalan.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestSingleResponse<T> : RestCommonResponse() {

    @SerializedName("data")
    @Expose
    var data: T ? = null

}