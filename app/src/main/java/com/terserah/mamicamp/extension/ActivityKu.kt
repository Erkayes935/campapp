package com.terserah.mamicamp.extension

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.View
import timber.log.Timber

fun tampilkanme(){
    Timber.d("Hi, Good Night!!")
}

fun tampilkanyou(){
    Timber.d("Good Night You")
}

fun tampilanthis(param1: String,param2: String) {
    Timber.d("parameter satu $param1")
    Timber.d("parameter dua $param2")
}

fun Activity.isGrantedLocation(): Boolean {
    return ActivityCompat.checkSelfPermission(this,
        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

fun Context.hmm():String {
    return "?????"
}

fun View.hmm():Int {
    return 282
}
