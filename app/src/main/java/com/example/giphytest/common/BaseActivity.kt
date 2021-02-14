package com.example.giphytest.common

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.giphytest.entity.room.DummyUserDao
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    @JvmField
    @Inject
    var dbInvoked: DummyUserDao? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

   open fun initialized() {}
   open fun listener() {}

    override fun onClick(p0: View?) {

    }

}