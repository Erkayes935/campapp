package com.terserah.mamicamp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.terserah.mamicamp.extension.tampilkanStevenBosku
import com.terserah.mamicamp.pojo.EmployeePojo
import kotlinx.android.synthetic.main.layout_employe_list.*

class EmployeeListActivity : AppCompatActivity() {

    var adapterBebas: EmployeeAdapter? = null
    var datanya: ArrayList<EmployeePojo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_employe_list)
        adapterBebas = EmployeeAdapter(this, datanya)
        recyclerEmployee.adapter = adapterBebas
        getData()

        tampilkanStevenBosku("Seteven lagi makan", "Steven lagi main PES")
    }

    private fun getData() {
        val baseUrlGet = "http://dummy.restapiexample.com/api/v1/employees"
        baseUrlGet
            .httpGet()
            .responseString { request, response, result ->
                showData(result.component1())
            }
    }

    private fun showData(result: String?) {
        val listType = object : TypeToken<ArrayList<EmployeePojo>>() {
        }.type

        datanya.addAll(Gson().fromJson(result, listType))
        datanya.reverse()
        Log.e("response", "${datanya.size}")///

        recyclerEmployee.postDelayed({
            adapterBebas?.notifyDataSetChanged()
            progress.visibility = View.GONE
        }, 500)

    }

}