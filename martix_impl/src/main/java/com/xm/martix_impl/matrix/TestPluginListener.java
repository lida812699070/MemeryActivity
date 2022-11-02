package com.xm.martix_impl.matrix;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.tencent.matrix.plugin.DefaultPluginListener;
import com.tencent.matrix.report.Issue;

public class TestPluginListener extends DefaultPluginListener {
    public static final String TAG = "Matrix.TestPluginListener";

    public TestPluginListener(Context context) {
        super(context);

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onReportIssue(Issue issue) {
        super.onReportIssue(issue);
        Log.e(TAG, issue.toString());
        //add your code to process data
    }
}