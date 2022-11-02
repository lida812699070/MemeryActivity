package com.example.memeryactivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tvBtn2 = (TextView) findViewById(R.id.tvBtn2);
        mHandler = new MyHandler();
        tvBtn2.setOnClickListener((View.OnClickListener) v -> {
//            mHandler.sendMessageDelayed(Message.obtain(), 10 * 60 * 1000);
//            finish();
            try {
                aaa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        String[] strs = null;
//        Integer aa = null;
//        System.out.println(strs[1]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("tag1", "MainActivity4 onPause");
    }

    private void aaa() {
        bbb();
    }

    private void bbb() {
        int i = 1 / 0;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.e("tag1", "handler 执行完成");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeCallbacksAndMessages(null);
    }
}
