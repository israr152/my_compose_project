package com.sofit.israr.composeproject.utils

import android.os.Build
import android.os.Bundle
import android.util.Log
import java.io.Serializable

fun String.log(tag:String = "TESTING"){
    Log.e(tag, this)
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializable(key, T::class.java)
    else getSerializable(key) as? T
}