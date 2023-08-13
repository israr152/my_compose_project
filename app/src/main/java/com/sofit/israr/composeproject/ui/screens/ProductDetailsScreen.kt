package com.sofit.israr.composeproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sofit.israr.composeproject.models.ProductDetailsModel
import com.sofit.israr.composeproject.ui.ErrorContent
import com.sofit.israr.composeproject.ui.ProgressContent
import com.sofit.israr.composeproject.viewModels.FlowState
import com.sofit.israr.composeproject.viewModels.IndexViewModel

@Composable
fun ProductDetailsScreen(navController: NavController, vm: IndexViewModel, productId: Int) {
    vm.loadProductDetails(productId)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(backgroundColor = Color.Blue, elevation = 5.dp) {
                Text(
                    text = "Product Details",
                    modifier = Modifier.padding(start = 6.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) {
        when (val response = vm.productDetails.collectAsState().value) {
            is FlowState.Loading -> {
                ProgressContent()
            }
            is FlowState.Failed -> {
                ErrorContent(msg = response.t?.message ?: "", vm)
            }
            is FlowState.ProductDetails -> {
                DetailsContent(response.data)
            }
            else -> {}
        }
    }
}

@Composable
private fun DetailsContent(product: ProductDetailsModel) {
    ConstraintLayout {
        val (image, title, category, brand, div, desc) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(product.images[0])
                    .crossfade(true)
                    .build()
            ),
            contentDescription = "Product Image",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .constrainAs(image){top.linkTo(parent.top)}
        )

        Text(
            text = product.title,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(title) { top.linkTo(image.bottom, margin = 10.dp) },
            color = MaterialTheme.colors.onBackground
        )

        Text(
            text = "Category: ${product.category}",
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(category) { top.linkTo(title.bottom, margin = 4.dp) },
            color = MaterialTheme.colors.onBackground
        )

        Text(
            text = "Brand: ${product.brand}",
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(brand) { top.linkTo(category.bottom, margin = 4.dp) },
            color = MaterialTheme.colors.onBackground
        )

        Divider(
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(div) { top.linkTo(brand.bottom, margin = 4.dp) },
        )

        Text(
            text = "Description: ${product.description}",
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(desc) { top.linkTo(div.bottom, margin = 4.dp) },
            color = MaterialTheme.colors.onBackground
        )
    }
}