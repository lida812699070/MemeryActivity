package com.example.memeryactivity

import android.app.Application
import android.util.Log
import com.example.memeryactivity.matrix.DynamicConfigImplDemo
import com.example.memeryactivity.matrix.TestPluginListener
import com.tencent.matrix.Matrix
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import java.io.File
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
        val builder = Matrix.Builder(this)
        builder.pluginListener(TestPluginListener(this)) // add general pluginListener
        val dynamicConfig = DynamicConfigImplDemo()
        val tracePlugin: TracePlugin = configureTracePlugin(dynamicConfig)
        builder.plugin(tracePlugin)
        Matrix.init(builder.build())
        Matrix.with().startAllPlugins()
    }

    private fun configureTracePlugin(dynamicConfig: DynamicConfigImplDemo): TracePlugin {
        val fpsEnable = dynamicConfig.isFPSEnable
        val traceEnable = dynamicConfig.isTraceEnable
        val signalAnrTraceEnable = dynamicConfig.isSignalAnrTraceEnable
        val traceFileDir = File(applicationContext.filesDir, "matrix_trace")
        if (!traceFileDir.exists()) {
            if (traceFileDir.mkdirs()) {
                MatrixLog.e(
                    "Matrix",
                    "failed to create traceFileDir"
                )
            }
        }
        val anrTraceFile = File(
            traceFileDir,
            "anr_trace"
        ) // path : /data/user/0/sample.tencent.matrix/files/matrix_trace/anr_trace
        val printTraceFile = File(
            traceFileDir,
            "print_trace"
        ) // path : /data/user/0/sample.tencent.matrix/files/matrix_trace/print_trace
        val traceConfig = TraceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .enableFPS(fpsEnable)
            .enableEvilMethodTrace(traceEnable)
            .enableAnrTrace(traceEnable)
            .enableStartup(traceEnable)
            .enableIdleHandlerTrace(traceEnable) // Introduced in Matrix 2.0
            .enableMainThreadPriorityTrace(true) // Introduced in Matrix 2.0
            .enableSignalAnrTrace(signalAnrTraceEnable) // Introduced in Matrix 2.0
            .anrTracePath(anrTraceFile.absolutePath)
            .printTracePath(printTraceFile.absolutePath)
            .splashActivities("sample.tencent.matrix.SplashActivity;")
            .isDebug(true)
            .isDevEnv(false)
            .build()

        //Another way to use SignalAnrTracer separately
        //useSignalAnrTraceAlone(anrTraceFile.getAbsolutePath(), printTraceFile.getAbsolutePath());
        return TracePlugin(traceConfig)
    }
}