package com.albertomier.mydogcollection

import android.app.Application
import android.content.Context
import com.albertomier.mydogcollection.core.Preference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyDogCollectionApp : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var preference: Preference
        var instance: MyDogCollectionApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        preference = Preference(applicationContext)
        val context: Context = applicationContext()
    }
}