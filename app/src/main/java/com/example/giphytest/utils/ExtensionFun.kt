package com.example.giphytest.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.giphytest.BuildConfig

//Intent+Extenions
inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>(this)
    intent.init()
    if (requestCode == -1) startActivity(intent, options)
    else startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

fun Activity.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}



fun debugLog(tag: String, message: String) {
    if (BuildConfig.DEBUG)
        Log.e(tag, message)
}