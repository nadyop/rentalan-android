package com.gdn.rentalan.ui.product

import com.gdn.rentalan.api.ApiInterface
import com.gdn.rentalan.ui.base.BasePresenter
import javax.inject.Inject

class ProductDetailPresenter @Inject constructor(private val api: ApiInterface):
        BasePresenter(), ProductDetailContract.Presenter {

}