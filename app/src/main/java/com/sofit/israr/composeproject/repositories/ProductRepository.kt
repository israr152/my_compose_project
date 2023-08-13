package com.sofit.israr.composeproject.repositories

import com.sofit.israr.composeproject.api.InterfaceApi
import com.sofit.israr.composeproject.models.ProductDetailsModel
import com.sofit.israr.composeproject.models.ProductsModel
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val api : InterfaceApi){
    suspend fun getAllProducts() : Response<ProductsModel>{
        return api.getAllProducts()
    }

    suspend fun getProductDetails(empId: Int) : Response<ProductDetailsModel>{
        return api.getProductDetails(empId)
    }
}