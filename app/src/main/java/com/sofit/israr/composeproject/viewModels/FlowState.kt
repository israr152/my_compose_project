package com.sofit.israr.composeproject.viewModels

import com.sofit.israr.composeproject.models.ProductDetailsModel
import com.sofit.israr.composeproject.models.ProductsModel

sealed class FlowState {
    object Empty : FlowState()
    object Loading : FlowState()
    class Failed(val t : Throwable?) : FlowState()
    class Products(val data : ProductsModel) : FlowState()
    class ProductDetails(val data : ProductDetailsModel) : FlowState()
}
