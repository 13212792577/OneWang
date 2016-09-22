package com.wanglipeng.a32014.onewang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.adapter.MyReadActivityAdapter;
import com.wanglipeng.a32014.onewang.bean.ReadActivityData;
import com.wanglipeng.a32014.onewang.callback.CallBackData;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.httputils.OkHttpUtils;
import com.wanglipeng.a32014.onewang.path.PathContents;

import java.util.List;

public class ReadDialogActivity extends AppCompatActivity{

    TextView textView_title;
    ListView listView;
    RelativeLayout rl_color;
    String path_activity;
    Thread thread;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==8){
                String data = (String) msg.obj;
                Gson gson = new Gson();
                ReadActivityData readActivityData = gson.fromJson(data, ReadActivityData.class);
                List<ReadActivityData.DataBean> beanList = readActivityData.getData();
                MyReadActivityAdapter myReadActivityAdapter = new MyReadActivityAdapter(beanList,ReadDialogActivity.this);
                listView.setAdapter(myReadActivityAdapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_dialog);
        textView_title = (TextView) findViewById(R.id.text_read_activity_title);
        rl_color = (RelativeLayout) findViewById(R.id.rl_color);
        listView = (ListView) findViewById(R.id.list_read_activity);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String id = intent.getStringExtra("id");
        path_activity = String.format(PathContents.READING.READING_AD_DETIL_PATH,id);
        String bgcolor = intent.getStringExtra("bgcolor");
        String bottom_text = intent.getStringExtra("bottom_text");
        String cover = intent.getStringExtra("cover");
        rl_color.setBackgroundColor(Color.parseColor(bgcolor));
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.parseColor(bgcolor));
        }
        textView_title.setText(title);
        //listview的底部添加
        initListViewBottom(bottom_text,cover);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getStringByNetwork(path_activity);
                handler.obtainMessage(8,data).sendToTarget();
            }
        });
        thread.start();

    }

    private void initListViewBottom(String bottom_text,String picUrl) {
        View view = LayoutInflater.from(this).inflate(R.layout.read_activity_bottom,null);
        TextView view_text = (TextView) view.findViewById(R.id.view_text);
        ImageView image = (ImageView) view.findViewById(R.id.view_image);
        view_text.setText("“"+bottom_text+"”");
        Picasso.with(this).load(picUrl).into(image);
        listView.addFooterView(view);
    }

    public void clickClose(View view){
        if(view.getId()==R.id.text_read_activity_close){
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        thread.interrupt();
        overridePendingTransition(0,R.anim.activity_out);
    }
}
