package com.deromang.test

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

class App : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        // Init instance
        instance = this
    }


    companion object {

        fun getContext(): Context? {
            return instance?.applicationContext
        }

        private var instance: App? = null
    }


}