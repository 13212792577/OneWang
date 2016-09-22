package com.wanglipeng.a32014.onewang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.bean.ReadData;

import java.util.List;

/**
 * Created by wanglipeng on 2016/9/20.
 */
public class MyListViewAdapter extends BaseAdapter{
    ReadData readData;
    Context context;
    int pos;
    List<ReadData.DataBean.EssayBean> essayBeanList ;
    List<ReadData.DataBean.SerialBean> serialBeanList ;
    List<ReadData.DataBean.QuestionBean> questionBeanList ;

    public MyListViewAdapter(ReadData readData, Context context,int pos) {
        this.readData = readData;
        this.context = context;
        this.pos = pos;
        essayBeanList = readData.getData().getEssay();
        serialBeanList = readData.getData().getSerial();
        questionBeanList = readData.getData().getQuestion();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_read,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (position){
            case 0:
                viewHolder.textView_title.setText(essayBeanList.get(pos).getHp_title());
                Log.e("=======","====textView_title=="+essayBeanList.get(pos).getHp_title());
                viewHolder.textView_name.setText(essayBeanList.get(pos).getAuthor().get(0).getUser_name());
                viewHolder.textView_word.setText(essayBeanList.get(pos).getGuide_word());
                viewHolder.textView_type.setText("短篇");
                break;
            case 1:
                viewHolder.textView_title.setText(serialBeanList.get(pos).getTitle());
                viewHolder.textView_name.setText(serialBeanList.get(pos).getAuthor().getUser_name());
                viewHolder.textView_word.setText(serialBeanList.get(pos).getExcerpt());
                viewHolder.textView_type.setText("连载");
                break;
            case 2:
                viewHolder.textView_title.setText(questionBeanList.get(pos).getQuestion_title());
                viewHolder.textView_name.setText(questionBeanList.get(pos).getAnswer_title());
                viewHolder.textView_word.setText(questionBeanList.get(pos).getAnswer_content());
                viewHolder.textView_type.setText("问答");
                break;
        }
        return convertView;
    }

    class ViewHolder{
        TextView textView_title,textView_name,textView_word,textView_type;
        View view;

        public ViewHolder(View view) {
            this.view = view;
            textView_title = (TextView) view.findViewById(R.id.text_read_title);
            textView_name = (TextView) view.findViewById(R.id.text_read_name);
            textView_word = (TextView) view.findViewById(R.id.text_read_word);
            textView_type = (TextView) view.findViewById(R.id.text_read_type);
        }
    }
}
