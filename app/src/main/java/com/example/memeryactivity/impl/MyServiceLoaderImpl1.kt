package com.example.memeryactivity.impl

import com.example.memeryactivity.IMyServiceLoader

class MyServiceLoaderImpl1 :IMyServiceLoader{
    override fun sayHello(): String {
        return "MyServiceLoaderImpl1"
    }
}