package com.wanglipeng.a32014.onewang.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.json.JsonToReadPictureUrl;
import com.wanglipeng.a32014.onewang.path.PathContents;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout layout_point;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==9){
                String data = (String) msg.obj;
                //轮播图的实现
                initViewPager(data);
            }
        }
    };

    private void initViewPager(String data) {
        String[] str = JsonToReadPictureUrl.getReadPictureUrl(data);
        int length = str.length;

    }

    public ReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_pager);
        layout_point = (LinearLayout) view.findViewById(R.id.linear_point);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getStringByNetwork(PathContents.READING.READING_AD_PATH);
                handler.obtainMessage(9,data).sendToTarget();
            }
        }).start();
        return view;
    }

}
