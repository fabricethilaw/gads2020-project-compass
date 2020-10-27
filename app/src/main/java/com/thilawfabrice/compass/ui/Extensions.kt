package com.thilawfabrice.compass.ui

import android.os.Handler
import android.os.Looper

fun runOnMainThread(function: () -> Unit) {
    val handler = Handler(Looper.getMainLooper()).post { function() }
}