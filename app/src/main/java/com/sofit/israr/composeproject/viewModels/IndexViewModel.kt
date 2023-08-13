package com.sofit.israr.composeproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sofit.israr.composeproject.models.ApiError
import com.sofit.israr.composeproject.repositories.ProductRepository
import com.sofit.israr.composeproject.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {
    private val _products : MutableStateFlow<FlowState> = MutableStateFlow(FlowState.Empty)
    val products : StateFlow<FlowState> = _products

    private val _productDetails : MutableStateFlow<FlowState> = MutableStateFlow(FlowState.Empty)
    val productDetails : StateFlow<FlowState> = _productDetails

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _products.value = FlowState.Loading
            val response = repository.getAllProducts()
            if(response.isSuccessful){
                _products.value = response.body()?.let { FlowState.Products(it) } ?: FlowState.Failed(Throwable("Error: ${getErrorMessage(response.errorBody())}"))
            }else{
                _products.value = FlowState.Failed(Throwable("Error: ${getErrorMessage(response.errorBody())}"))
            }
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody?): String {
        errorBody?:return ""
        "errorMessage: ${errorBody.charStream().readText()}".log()
        return try {
            Gson().fromJson(errorBody.charStream().readText(), ApiError::class.java).message
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    fun loadProductDetails(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _productDetails.value = FlowState.Loading
            val response = repository.getProductDetails(productId)
            if(response.isSuccessful){
                _productDetails.value = response.body()?.let { FlowState.ProductDetails(it) } ?: FlowState.Failed(Throwable("Error:true= ${getErrorMessage(response.errorBody())}"))
            }else{
                _productDetails.value = FlowState.Failed(Throwable("Error:false= ${getErrorMessage(response.errorBody())}"))
            }
        }
    }
}