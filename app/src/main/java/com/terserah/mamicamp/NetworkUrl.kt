package com.terserah.mamicamp

import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.terserah.mamicamp.pojo.EmployeePojo

object NetworkUrl {

    private val KEY_BASE_URL = "http://dummy.restapiexample.com/api/v1/"
    private val KEY_SEND_EMPLOYEE = "create"
    private val KEY_GET_EMPLOYEE = "employees"

    fun sendData() {
        Log.i("MamiCamp", "Check pojo ${EmployeePojo("Mark Zuckerberg", "12", "120000").toString()}")
        val url = KEY_BASE_URL+ KEY_SEND_EMPLOYEE
        url.httpPost()
            .body(EmployeePojo("Mark Zuckerberg", "12", "120000").toString())
            .response { request, response, result ->
                Log.i("MamiCamp", "Netrwork uri .... $response")
            }
    }
}