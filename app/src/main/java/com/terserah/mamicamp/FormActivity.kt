package com.terserah.mamicamp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bugsnag.android.Bugsnag
import com.terserah.mamicamp.extension.isGrantedLocation
import com.terserah.mamicamp.extension.stevenJahat
import com.terserah.mamicamp.extension.tampilkanStevenMalamJumatan
import com.terserah.mamicamp.network.MamiCampUrl
import com.terserah.mamicamp.pojo.EmployeSenderPojo
import com.terserah.mamicamp.pojo.EmployeePojo
import kotlinx.android.synthetic.main.layout_form.*
import timber.log.Timber
import java.util.jar.Manifest

/*
* 1. Buat Pojo Employee
* 2. Buat Adapter Employee
* 3. Buat Activity Employee RecyclerView dan buat button utk mengarahkan ke list
* 4. Setup data dummy Activity Employee
* 5. Import Fuel Rest Client
* 6. Permission (internet)
* */

class FormActivity : AppCompatActivity(), StevenInteface {
    override fun stevenJahat() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_form)

        accessLocation()

        LihatDataButton.setOnClickListener {
            val intent = Intent(FormActivity@this, EmployeeListActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            validateForm()
        }

        checkArray()

        tampilkanStevenMalamJumatan()

        com.terserah.mamicamp.extension.stevenJahat(this)
    }

    private fun checkArray(){
        val empl = EmployeePojo("Dedi", "", "")
        val empi = EmployeePojo("Steven", "", "")
        val emplArray: MutableList<EmployeePojo> = arrayListOf()

        emplArray.add(0, empl)
        emplArray.add(1, empi)

        for (dedi in emplArray) {
            Timber.d("Check for Dedi ${dedi.employeeName}")
        }
    }

    private fun accessLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isGrantedLocation()) {
                showRequestPermission()
            }
        } else {
            Timber.d("Android permission not required")
        }
    }

    private fun validateForm() {

        mainProgressBar.visibility = View.VISIBLE

        val name = editText.text.toString()
        val salary = editText3.text.toString()
        val age = umurEdittext.text.toString()

        var checkA = "Length"

        if (name.length < 3) {
            Toast.makeText(this, "Nama Anda terlalu singkat", Toast.LENGTH_SHORT).show()
            dismissLoading()
        } else {
            val employePojo = EmployeSenderPojo(name, salary, age)
            Timber.d("Send Pojo $employePojo")

            MamiCampUrl.sendEmployee(employePojo)?.let {
                Log.i("FormActivity", "Response are $it")
            }
            dismissLoading()
        }

        try {
            checkA.get(100)
        } catch (exception: Throwable) {
            Bugsnag.notify(exception)
        }

    }

    private fun dismissLoading() {
        mainProgressBar.postDelayed({
                mainProgressBar.visibility = View.GONE
            }, 2000)
    }

    private fun showRequestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA))
        {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Ceks Dungs .... ")
            alertDialog.setPositiveButton("Ya") { dialog, which ->
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 100)
            }
            alertDialog.setNegativeButton("Tidak") { dialog, which ->
                Toast.makeText(this, "Anda menolak permission lokasi", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 100) {

            Timber.d("Check $grantResults")
            for (index in grantResults) {
                Timber.d("Check Index $index")
                if (index == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Diterima cieeee ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ditolak .....", Toast.LENGTH_SHORT).show()
                    showRequestPermission()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}