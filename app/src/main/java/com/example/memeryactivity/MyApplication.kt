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
        val serviceLoader = ServiceLoader.load(IMyServiceLoader::class.java)
        serviceLoader.forEach {
            Log.e("tag1", "${it.sayHello()}")
        }
        newInstant = this
        Log.e("tag1", "=========")

        initMatrix()
    }

    private fun initMatrix() {
        try {
            val serviceLoader = ServiceLoader.load(IMatrixManager::class.java)
            serviceLoader.forEach {
                it.init(this)
                Log.e("tag1", "IMatrixManager")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}