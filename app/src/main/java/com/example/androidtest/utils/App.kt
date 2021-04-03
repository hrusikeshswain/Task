package com.example.androidtest.utils

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.bumptech.glide.request.target.ViewTarget
import com.example.androidtest.R
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*


/**
 * Created by Hrusikesh swain on 6/7/2020.
 * Be U Salons
 * hrusikeshswain@beusalons.com
 */

class App : Application() {

    companion object{
        private var activityVisible = false

        fun isActivityVisible(): Boolean {
            return activityVisible
        }

        fun activityResumed() {
            activityVisible = true
        }

        fun activityPaused() {
            activityVisible = false
        }

        private var foreground = false
        fun isForeground() : Boolean {
            return foreground
        }

        fun showToast(context: Context,msg:String){
            val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
            toast.show()
        }
    }

    override fun onCreate() {
        super.onCreate()
        /*AndroidtestSharedPrefrence.init(applicationContext)
        AndroidtestSharedPrefrence.setAccessToken(applicationContext.getString(R.string.accesstoken))*/

    }



    internal class InternetCheck(private val mConsumer: Consumer) : AsyncTask<Void?, Void?, Boolean>() {

        interface Consumer {
            fun accept(internet: Boolean)
        }

        override fun onPostExecute(internet: Boolean) {
            mConsumer.accept(internet)
        }

        init {
            execute()
        }

        override fun doInBackground(vararg p0: Void?): Boolean {
            return try {
                val sock = Socket()
                sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                sock.close()
                true
            } catch (e: IOException) {
                false
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        foreground = false

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        // App in foreground
        foreground = true
    }
}