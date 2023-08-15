package com.sofit.israr.composeproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.sofit.israr.composeproject.models.Product
import com.sofit.israr.composeproject.ui.ErrorContent
import com.sofit.israr.composeproject.ui.ProductRow
import com.sofit.israr.composeproject.ui.ProgressContent
import com.sofit.israr.composeproject.ui.Screens
import com.sofit.israr.composeproject.viewModels.FlowState
import com.sofit.israr.composeproject.viewModels.IndexViewModel

@Composable
fun IndexScreen(navController: NavController, vm: IndexViewModel){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(backgroundColor = Color.Blue, elevation = 5.dp) {
                Text(text = "Products", modifier = Modifier.padding(start = 6.dp), color = Color.White, textAlign = TextAlign.Center)
            }
        }
    ) {
        when(val response = vm.products.collectAsState().value){
            is FlowState.Loading -> {
                ProgressContent()
            }
            is FlowState.Failed -> {
                ErrorContent(msg = response.t?.message?:"", vm)
            }
            is FlowState.Products -> {
                MainContent(navController, list = response.data.products)
            }
            else -> {}
        }
    }
}

@Composable
private fun MainContent(navController: NavController, list: List<Product>) {
    Column(modifier = Modifier.padding(6.dp)) {
        LazyColumn {
            items(list){
                ProductRow(it){ id->
                    navController.navigate(route = Screens.DetailsScreen.name){
                        val pString = Gson().toJson(it)
                        navController.currentBackStackEntry?.savedStateHandle?.set("product" ,pString)
                    }
                }
            }
        }
    }
}