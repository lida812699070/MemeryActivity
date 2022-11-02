package com.xm.martix_impl.matrix

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TestPluginListener(var context: Context?) : DefaultPluginListener(context) {
    companion object {
        const val TAG = "Matrix.TestPluginListener"
    }

    @SuppressLint("LongLogTag")
    override fun onReportIssue(issue: Issue) {
        super.onReportIssue(issue)
        Log.e(TAG, issue.toString())
        logInFile(issue.toString())
    }

    private fun logInFile(txt: String) {
        try {
            val application = context as Application
            val format = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
            val timeStr = format.format(Date())
            val cacheDir = File("${application.cacheDir}/matrix_block_log")
            val file = File(cacheDir, "${timeStr}.txt")
            if (cacheDir.exists().not()) {
                cacheDir.mkdirs()
            }
            file.writeText(txt)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}