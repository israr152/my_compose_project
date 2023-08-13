package com.sofit.israr.composeproject.api

import com.sofit.israr.composeproject.models.ProductDetailsModel
import com.sofit.israr.composeproject.models.ProductsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceApi {
    @GET("products")
    suspend fun getAllProducts(): Response<ProductsModel>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id : Int): Response<ProductDetailsModel>
}