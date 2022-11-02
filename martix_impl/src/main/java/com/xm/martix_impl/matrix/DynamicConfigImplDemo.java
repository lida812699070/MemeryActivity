package com.xm.martix_impl.matrix;/*
 * Tencent is pleased to support the open source community by making wechat-matrix available.
 * Copyright (C) 2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author zhoushaotao
 * Created by zhoushaotao on 2018/10/9.
 */


import android.util.Log;

import com.tencent.matrix.util.MatrixLog;
import com.tencent.mrs.plugin.IDynamicConfig;

import java.util.concurrent.TimeUnit;

public class DynamicConfigImplDemo implements IDynamicConfig {
    private static final String TAG = "Matrix.DynamicConfigImplDemo";

    public DynamicConfigImplDemo() {

    }

    public boolean isFPSEnable() {
        return true;
    }

    public boolean isTraceEnable() {
        return true;
    }

    public boolean isSignalAnrTraceEnable() {
        return true;
    }

    public boolean isMatrixEnable() {
        return true;
    }

    @Override
    public String get(String key, String defStr) {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.

        // for Activity leak detect
        if ((ExptEnum.clicfg_matrix_resource_detect_interval_millis.name().equals(key) || ExptEnum.clicfg_matrix_resource_detect_interval_millis_bg.name().equals(key))) {
            Log.d("DynamicConfig", "Matrix.ActivityRefWatcher: clicfg_matrix_resource_detect_interval_millis 10s");
            return String.valueOf(TimeUnit.SECONDS.toMillis(5));
        }

        if (ExptEnum.clicfg_matrix_resource_max_detect_times.name().equals(key)) {
            Log.d("DynamicConfig", "Matrix.ActivityRefWatcher: clicfg_matrix_resource_max_detect_times 5");
            return String.valueOf(3);
        }

        return defStr;
    }


    @Override
    public int get(String key, int defInt) {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.

        if (ExptEnum.clicfg_matrix_resource_max_detect_times.name().equals(key)) {
            MatrixLog.i(TAG, "key:" + key + ", before change:" + defInt + ", after change, value:" + 2);
            return 2;//new value
        }

        //卡顿多少秒就打印
        /**
         * >>>>>>>>>>>>>>>>>>>>> maybe happens Jankiness!(2018ms) <<<<<<<<<<<<<<<<<<<<<
         *     |* [Status]
         *     |*		Scene: com.example.memeryactivity.MainActivity
         *     |*		Foreground: true
         *     |*		Priority: 10	Nice: -10
         *     |*		is64BitRuntime: true
         *     |*		CPU: 0.30%
         *     |* [doFrame]
         *     |*		inputCost:animationCost:traversalCost
         *     |*		0:0:0
         *     |*		StackKey: 961|
         *     |*		TraceStack:
         *     |*		[id count cost]
         *     |*		1048574 1 2018
         *     |*		.8192 1 0
         *     |*		.961 1 2013
         *     |*		..14132 1 0
         *     |*		..443 1 11
         *     |*		...451 1 11
         *     =========================================================================
         */
        if (ExptEnum.clicfg_matrix_trace_evil_method_threshold.name().equals(key)) {
            return 700;
        }

        if (ExptEnum.clicfg_matrix_trace_fps_time_slice.name().equals(key)) {
            return 12000;
        }

        return defInt;

    }

    @Override
    public long get(String key, long defLong) {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.
//        if (ExptEnum.clicfg_matrix_trace_fps_report_threshold.name().equals(key)) {
//            return 10000L;
//        }

        if (ExptEnum.clicfg_matrix_resource_detect_interval_millis.name().equals(key)) {
            MatrixLog.i(TAG, key + ", before change:" + defLong + ", after change, value:" + 2000);
            return 2000;
        }

        return defLong;
    }


    @Override
    public boolean get(String key, boolean defBool) {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.

        return defBool;
    }

    @Override
    public float get(String key, float defFloat) {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.

        return defFloat;
    }

}
