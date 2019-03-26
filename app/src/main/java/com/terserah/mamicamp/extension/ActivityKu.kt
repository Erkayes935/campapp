package com.terserah.mamicamp.extension

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.View
import com.terserah.mamicamp.StevenInteface
import timber.log.Timber

fun tampilkanStevenMalamJumatan(){
    Timber.d("Hallo steven, Selamat Malam jumatan!")
}

fun tampilkanStevenMalamMinggu(){
    Timber.d("Hallo steven, Selamat Malam mingguan!")
}

fun tampilkanStevenBosku(ikiParameterSiji: String, ikiParameterLoro: String){
    Timber.d("parameter siji $ikiParameterSiji")
    Timber.d("parameter loro $ikiParameterLoro")
}

fun Activity.isGrantedLocation(): Boolean {
    return ActivityCompat.checkSelfPermission(this,
        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}

fun Context.stevenJahat(): String {
    return "Iyo ee"
}

fun stevenJahat(stevenInteface: StevenInteface): Int {
    return 282
}