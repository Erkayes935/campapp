package com.terserah.mamicamp

import android.os.Parcelable
import com.terserah.mamicamp.pojo.EmployeePojo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EmployeeResponse(var dataNe: MutableList<EmployeePojo> = arrayListOf()) : Parcelable