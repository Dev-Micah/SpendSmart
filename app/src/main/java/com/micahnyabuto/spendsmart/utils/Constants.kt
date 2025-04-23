package com.micahnyabuto.spendsmart.utils

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.micahnyabuto.spendsmart.model.Category

const val FOOD = "Food stuffs"
const val TRANSPORT ="Transport"
const val CLOTHING = "Clothing"
const val SHELTER = "Accommodation & House bills"
const val TRANSACTION_FEES = "Transaction fees"

val categories = listOf(
    Category(FOOD, Color(0xFF2196F2)),
    Category(TRANSPORT, Color(0xff008000)),
    Category(CLOTHING, Color(0xff808080)),
    Category(SHELTER, Color(0xFF4DD6EE)),
    Category(TRANSACTION_FEES, Color(0xffA52A2A))
)

/**
 * Time stamp to return formatted date
 * e.g 1633660800000 -> 7th October 2021
 */

@SuppressLint("SimpleDateFormat")
fun Long.timestampToDate(): String {
    return try {
        val date =java.util.Date(this)
        val formatter = java.text.SimpleDateFormat("d MMMM yyyy")
        formatter.format(date)
    } catch (e: Exception){
        this.toString()
    }
}
/**
 * Time stamp to return formatted date
 * e.g 1633660800000 -> 7th October 2021 9:00 AM
 */

@SuppressLint("SimpleDateFormat")
fun Long.timestampToDateTime(): String {
    return try {
        val date =java.util.Date(this)
        val formatter = java.text.SimpleDateFormat("MMM dd, yyyy hh:mm a")
        formatter.format(date)
    } catch (e: Exception){
        this.toString()
    }
}