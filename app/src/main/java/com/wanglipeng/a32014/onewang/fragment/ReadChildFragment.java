package com.wanglipeng.a32014.onewang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.adapter.MyListViewAdapter;
import com.wanglipeng.a32014.onewang.bean.ReadData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadChildFragment extends LazyFragment {


    boolean flag;
    ListView listView;
    public ReadChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_child, container, false);
        listView = (ListView) view.findViewById(R.id.listview_read);
        flag=true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if(!flag || !isVisible){
            return;
        }
        Bundle bundle = getArguments();
        int pos = bundle.getInt("pos");
        String data = bundle.getString("data");
        Gson gson = new Gson();
        ReadData readData = gson.fromJson(data, ReadData.class);
        List<ReadData.DataBean.EssayBean> essayBeanList = readData.getData().getEssay();
        List<ReadData.DataBean.SerialBean> serialBeanList = readData.getData().getSerial();
        List<ReadData.DataBean.QuestionBean> questionBeanList = readData.getData().getQuestion();
        MyListViewAdapter myListViewAdapter = new MyListViewAdapter(readData,getActivity(),pos);
        listView.setAdapter(myListViewAdapter);
    }
}
