package com.thilawfabrice.compass.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.thilawfabrice.compass.Compass

fun runOnMainThread(function: () -> Unit) {
    val handler = Handler(Looper.getMainLooper()).post { function() }
}

fun Fragment.getVewModel() = (requireContext().applicationContext as Compass).tipViewModel