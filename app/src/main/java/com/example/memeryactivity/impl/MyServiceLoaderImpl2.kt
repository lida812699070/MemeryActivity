package com.example.memeryactivity.impl

import com.example.memeryactivity.IMyServiceLoader

class MyServiceLoaderImpl2 :IMyServiceLoader{
    override fun sayHello(): String {
        return "MyServiceLoaderImpl2"
    }
}