package com.wanglipeng.a32014.onewang.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.adapter.MyHomeAdapter;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.json.JsonToHomePage;
import com.wanglipeng.a32014.onewang.path.PathContents;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==10){
                String data = (String) msg.obj;
                if(data!=null){
                    String[] array = JsonToHomePage.getHomePage(data);
                    int length = array.length;
                    FragmentManager fragmentManager = getFragmentManager();
                    List<Fragment> fragmentList = new ArrayList<>();
                    for(int i=0;i<length;i++){
                        HomeContentFragment homeContentFragment = new HomeContentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("page",array[i]);
                        homeContentFragment.setArguments(bundle);
                        fragmentList.add(homeContentFragment);
                    }
                    MyHomeAdapter myHomeAdapter = new MyHomeAdapter(fragmentManager,fragmentList);
                    viewPager.setAdapter(myHomeAdapter);
                }else{
                    Toast.makeText(getActivity(), "无网络连接", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    View view;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_home);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getStringByNetwork(PathContents.HOME.HOME_PATH);

                handler.obtainMessage(10,data).sendToTarget();
            }
        }).start();

        return view;
    }
}
