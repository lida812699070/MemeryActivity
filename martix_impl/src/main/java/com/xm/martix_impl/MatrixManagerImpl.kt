package com.xm.martix_impl

import android.app.Application
import com.tencent.matrix.Matrix
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import com.xm.martix_impl.matrix.DynamicConfigImplDemo
import com.xm.martix_impl.matrix.TestPluginListener
import com.xm.martix_interface.IMatrixManager
import java.io.File

class MatrixManagerImpl : IMatrixManager {
    override fun init(application: Application) {
        val builder = Matrix.Builder(application)
        builder.pluginListener(TestPluginListener(application)) // add general pluginListener
        val dynamicConfig = DynamicConfigImplDemo()
        val tracePlugin: TracePlugin = configureTracePlugin(application, dynamicConfig)
        builder.plugin(tracePlugin)
        Matrix.init(builder.build())
        Matrix.with().startAllPlugins()
    }

    private fun configureTracePlugin(
        context: Application,
        dynamicConfig: DynamicConfigImplDemo
    ): TracePlugin {
        val fpsEnable = dynamicConfig.isFPSEnable
        val traceEnable = dynamicConfig.isTraceEnable
        val signalAnrTraceEnable = dynamicConfig.isSignalAnrTraceEnable
        val traceFileDir = File(context.filesDir, "matrix_trace")
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