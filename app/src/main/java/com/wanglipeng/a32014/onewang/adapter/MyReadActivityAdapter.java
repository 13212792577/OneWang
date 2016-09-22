package com.wanglipeng.a32014.onewang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.bean.ReadActivityData;

import java.util.List;

/**
 * Created by 32014 on 2016/9/21.
 */
public class MyReadActivityAdapter extends BaseAdapter{
    List<ReadActivityData.DataBean> beanList;
    Context context;

    public MyReadActivityAdapter(List<ReadActivityData.DataBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beanList.size();
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
        ViewHolderRead viewHolderRead;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_read_activity,null);
            viewHolderRead = new ViewHolderRead(convertView);
            convertView.setTag(viewHolderRead);
        }else{
            viewHolderRead = (ViewHolderRead) convertView.getTag();
        }
        viewHolderRead.textView_title.setText(beanList.get(position).getTitle());
        viewHolderRead.textView_name.setText(beanList.get(position).getAuthor());
        viewHolderRead.textView_word.setText(beanList.get(position).getIntroduction());
        viewHolderRead.textView_type.setText(String.valueOf(position+1));
        return convertView;
    }

     class ViewHolderRead{
         TextView textView_title,textView_name,textView_word,textView_type;
         View view;

         public ViewHolderRead(View view) {
             this.view = view;
             textView_title = (TextView) view.findViewById(R.id.item_read_title);
             textView_name = (TextView) view.findViewById(R.id.item_read_name);
             textView_word = (TextView) view.findViewById(R.id.item_read_word);
             textView_type = (TextView) view.findViewById(R.id.item_read_pos);
         }
     }
}
