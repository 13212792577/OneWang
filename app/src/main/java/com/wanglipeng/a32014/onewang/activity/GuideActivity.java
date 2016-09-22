package com.wanglipeng.a32014.onewang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.wanglipeng.a32014.onewang.R;

public class GuideActivity extends AppCompatActivity {

    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现单个activity全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        setContentView(R.layout.activity_guide);

        SharedPreferences sp = getSharedPreferences("wang_li_peng",MODE_PRIVATE);
        flag = sp.getBoolean("isFirst",true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
