package com.wanglipeng.a32014.onewang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.activity.MainActivity;
import com.wanglipeng.a32014.onewang.activity.ReadDialogActivity;
import com.wanglipeng.a32014.onewang.adapter.MyHomeAdapter;
import com.wanglipeng.a32014.onewang.adapter.MyListViewAdapter;
import com.wanglipeng.a32014.onewang.adapter.MyReadAdapter;
import com.wanglipeng.a32014.onewang.bean.ReadData;
import com.wanglipeng.a32014.onewang.bean.ReadTopData;
import com.wanglipeng.a32014.onewang.callback.CallBackData;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.httputils.OkHttpUtils;
import com.wanglipeng.a32014.onewang.json.JsonToReadPictureUrl;
import com.wanglipeng.a32014.onewang.path.PathContents;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment implements View.OnClickListener{

    ViewPager viewPager,viewPager_bottom;
    LinearLayout layout_point;
    int length;     //图片的个数
    List<ReadTopData.DataBean> dataBeanList; //图片的Id对象

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==9){
                Bundle bundle = msg.getData();
                String data1 = bundle.getString("data1");
                getBottomView(data1);
                String data = bundle.getString("data");
                //轮播图的实现
                initViewPager(data);
            }else if(msg.what==11){
                int temp = viewPager.getCurrentItem();
                viewPager.setCurrentItem((temp+1)%length);
                handler.removeMessages(11);
                handler.sendEmptyMessageDelayed(11,3000);
            }
        }
    };

    private void initViewPager(String data) {
        ImageView imageView;
        Gson gson = new Gson();
        ReadTopData readTopData = gson.fromJson(data, ReadTopData.class);
        dataBeanList = readTopData.getData();
        length = dataBeanList.size();
        List<View> viewList = new ArrayList<>();
        for(int i=0;i<length;i++){
            imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(dataBeanList.get(i).getCover()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(imageView);
            imageView.setTag(i);
            imageView.setOnClickListener(this);
            ImageView image_point = new ImageView(getActivity());
            image_point.setPadding(10,0,10,0);
            if(i==0){
                image_point.setImageResource(R.drawable.point_checked);
            }else{
                image_point.setImageResource(R.drawable.point);
            }
            layout_point.setGravity(Gravity.CENTER);
            layout_point.addView(image_point);
        }
        MyReadAdapter myReadAdapter = new MyReadAdapter(viewList);
        viewPager.setAdapter(myReadAdapter);
        handler.sendEmptyMessageDelayed(11,3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int num = layout_point.getChildCount();
                for(int i=0;i<num;i++){
                    ImageView view = (ImageView) layout_point.getChildAt(i);
                    if(i==position){
                        view.setImageResource(R.drawable.point_checked);
                    }else{
                        view.setImageResource(R.drawable.point);
                    }
                }
        }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        viewPager_bottom = (ViewPager) view.findViewById(R.id.vp_pager_bottom);
        layout_point = (LinearLayout) view.findViewById(R.id.linear_point);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getStringByNetwork(PathContents.READING.READING_AD_PATH);
                String data1 = HttpUtils.getStringByNetwork(PathContents.READING.READING_CONTENT_PATH);
                Bundle bundle = new Bundle();
                bundle.putString("data",data);
                bundle.putString("data1",data1);
                Message message = new Message();
                message.what=9;
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
        return view;
    }

    public void getBottomView(String data) {

        List<Fragment> fragmentList = new ArrayList<>();
        for(int i=0;i<10;i++){
            ReadChildFragment readChildFragment = new ReadChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString("data",data);
            bundle.putInt("pos",i);
            readChildFragment.setArguments(bundle);
            fragmentList.add(readChildFragment);
        }

        MyHomeAdapter myHomeAdapter = new MyHomeAdapter(getFragmentManager(),fragmentList);
        viewPager_bottom.setAdapter(myHomeAdapter);

    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        String id = dataBeanList.get(pos).getId();
        String bgcolor = dataBeanList.get(pos).getBgcolor();
        String title = dataBeanList.get(pos).getTitle();
        String bottom_text = dataBeanList.get(pos).getBottom_text();
        String cover = dataBeanList.get(pos).getCover();
        Intent intent = new Intent(getActivity(), ReadDialogActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("bgcolor",bgcolor);
        intent.putExtra("title",title);
        intent.putExtra("bottom_text",bottom_text);
        intent.putExtra("cover",cover);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_in,0);
    }
}
