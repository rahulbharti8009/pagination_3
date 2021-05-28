package com.kreate.daggerwithhilt.utils

import android.app.Activity
import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.intuit.sdp.BuildConfig
import java.util.*


fun log(log: String, msg: String) {
    if (BuildConfig.DEBUG)
        Log.d(log, msg)
}

fun showToast(context: Context, msg: String){
    Toast.makeText(context, "Message : $msg", Toast.LENGTH_LONG).show()
}


fun snackbar(message: String, activity: Activity = Activity()) {
    if (BuildConfig.DEBUG)
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
}

private fun getDate(time: Long): String? {
    val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
    cal.setTimeInMillis(time * 1000)
    return DateFormat.format("dd-MM-yyyy", cal).toString()
}