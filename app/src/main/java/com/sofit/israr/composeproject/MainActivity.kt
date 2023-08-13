package com.sofit.israr.composeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sofit.israr.composeproject.ui.MainNavigation
import com.sofit.israr.composeproject.ui.theme.ComposeProjectTheme
import com.sofit.israr.composeproject.viewModels.IndexViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm : IndexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                MainNavigation(vm)
            }
        }
    }
}