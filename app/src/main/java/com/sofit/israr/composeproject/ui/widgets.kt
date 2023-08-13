package com.sofit.israr.composeproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.sofit.israr.composeproject.models.Product
import com.sofit.israr.composeproject.viewModels.IndexViewModel

@Composable
fun ProductRow(product: Product, OnItemClick: (Int) -> Unit = {}){
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(6.dp)
        .clickable {
            OnItemClick(product.id)
        },
        shape = RoundedCornerShape(CornerSize(6.dp)),
        elevation = 10.dp
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(modifier = Modifier
                .size(100.dp)
                .padding(12.dp),
                shape = RectangleShape,
                elevation = 0.dp) {

                Image(painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(product.images[0])
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                ), contentDescription = "Product Image", alignment = Alignment.TopCenter)
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = product.title, style = MaterialTheme.typography.h6)
                Text(text = "Category: ${product.category}", style = MaterialTheme.typography.caption)
                Text(text =  "Price: ${product.price}", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun ErrorContent(msg: String, vm : IndexViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { vm.loadProducts() },verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = msg, style = MaterialTheme.typography.h6)
    }
}

@Composable
fun ProgressContent(){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}