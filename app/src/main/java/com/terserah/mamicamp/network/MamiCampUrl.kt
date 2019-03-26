package com.terserah.mamicamp.network

import android.app.Activity
import android.util.Log
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.terserah.mamicamp.extension.tampilkanStevenBosku
import com.terserah.mamicamp.pojo.EmployeSenderPojo
import timber.log.Timber

object MamiCampUrl {

    private val KEY_BASE_URL = "http://dummy.restapiexample.com/api/v1/"
    private val KEY_GET_EMPLOYEE = "employees"
    private val KEY_SEND_EMPLOYEE = "create"

    fun sendEmployee(param: EmployeSenderPojo): Response? {

        tampilkanStevenBosku("Oke sih", "Say hello")

        val urlSender = KEY_BASE_URL+KEY_SEND_EMPLOYEE
        val result: Response? = null
        urlSender.httpPost()
            .body(Gson().toJson(param))
            .responseString {
                request, response, result ->
                Timber.d("Response Employee $response")
                Timber.d("Response Employee $result")
                Timber.d("Response Employee $request")
            }
        return result
    }

}