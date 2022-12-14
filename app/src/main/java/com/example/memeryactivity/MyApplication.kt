package com.example.memeryactivity

import android.app.Application
import android.util.Log
import com.xm.martix_interface.IMatrixManager
import java.util.*


class MyApplication : Application() {

    companion object {
        var newInstant: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        newInstant = this
        initMatrix()
    }

    private fun initMatrix() {
        try {
            val serviceLoader = ServiceLoader.load(IMatrixManager::class.java)
            serviceLoader.forEach {
                it.init(this)
                Log.e("tag1", "IMatrixManager")
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}