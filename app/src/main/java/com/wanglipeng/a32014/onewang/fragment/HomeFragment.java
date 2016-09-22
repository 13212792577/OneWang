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
import com.wanglipeng.a32014.onewang.callback.CallBackData;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.httputils.OkHttpUtils;
import com.wanglipeng.a32014.onewang.json.JsonToHomePage;
import com.wanglipeng.a32014.onewang.path.PathContents;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_home);
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.getDataByNetwork(PathContents.HOME.HOME_PATH);
        okHttpUtils.setCallBackData(new CallBackData() {
            @Override
            public void getDataCallBack(String data,String path) {
                if(path.equals(PathContents.HOME.HOME_PATH)){
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
                }
            }
        });
        return view;
    }

}
