package com.wanglipeng.a32014.onewang.fragment;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.wanglipeng.a32014.onewang.R;
import com.wanglipeng.a32014.onewang.bean.HomeData;
import com.wanglipeng.a32014.onewang.httputils.HttpUtils;
import com.wanglipeng.a32014.onewang.path.PathContents;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeContentFragment extends Fragment implements View.OnClickListener{

    static boolean flag = false;
    int praisenum;
    String hp_img_url;
    boolean heart = true;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==5){
                String data = (String) msg.obj;
                if(data!=null){
                    Gson gson = new Gson();
                    HomeData homeData = gson.fromJson(data, HomeData.class);
                    hp_img_url = homeData.getData().getHp_img_url();
                    Picasso.with(getActivity()).load(hp_img_url).placeholder(R.drawable.default_hp_image).into(imageView);
                    textView1.setText(homeData.getData().getHp_title());
                    textView2.setText(homeData.getData().getHp_author());
                    textView3.setText(homeData.getData().getHp_content());
                    textView4.setText(homeData.getData().getHp_makettime());
                    praisenum = homeData.getData().getPraisenum();
                    textView5.setText(String.valueOf(praisenum));
                }else{
                    Toast.makeText(getActivity(), "无网络连接", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    TextView textView1,textView2,textView3,textView4,textView5;
    ImageView imageView,imageView2;

    public HomeContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_home);
        imageView2 = (ImageView) view.findViewById(R.id.image_heart);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);

        textView1 = (TextView) view.findViewById(R.id.hp_title);
        textView2 = (TextView) view.findViewById(R.id.hp_author);
        textView3 = (TextView) view.findViewById(R.id.hp_content);
        textView4 = (TextView) view.findViewById(R.id.hp_makettime);
        textView5 = (TextView) view.findViewById(R.id.text_heart);
        Bundle bundle = getArguments();
        String page = bundle.getString("page");
        final String path = String.format(PathContents.HOME.HOME_DETAIL_PATH,page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getStringByNetwork(path);
                handler.obtainMessage(5,data).sendToTarget();
            }
        }).start();
        flag = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            Bundle bundle = getArguments();
            String page = bundle.getString("page");
            if(flag){
                final String path = String.format(PathContents.HOME.HOME_DETAIL_PATH,page);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String data = HttpUtils.getStringByNetwork(path);
                        handler.obtainMessage(5,data).sendToTarget();
                    }
                }).start();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_home:
                doPicture();
                break;
            case R.id.image_heart:
                if(heart){
                    praisenum = praisenum+1;
                    textView5.setText(String.valueOf(praisenum));
                    imageView2.setBackgroundResource(R.drawable.user_laud_checked);
                    heart=false;
                }else{
                    praisenum = praisenum-1;
                    textView5.setText(String.valueOf(praisenum));
                    imageView2.setBackgroundResource(R.drawable.user_laud);
                    heart=true;
                }
                break;
        }
    }

    private void doPicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ImageView image = new ImageView(getActivity());
        Picasso.with(getActivity()).load(hp_img_url).into(image);
        builder.setView(image);
        final AlertDialog dialog = builder.create();
        dialog.show();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = width;
        window.setAttributes(lp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setWindowAnimations(R.style.WindowDialogAnimation);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_dialog_picture,null);
                builder.setView(view);
                final AlertDialog dialog1 = builder.create();
                dialog1.show();
                view.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                view.findViewById(R.id.text_save_picture).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            String path_sd = Environment.getExternalStorageDirectory().getAbsolutePath();
                            final String path_picture = path_sd+"/DCIM/Camera"+"/IMG_"+ System.currentTimeMillis()+".jpg";
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean result = HttpUtils.getPicture(hp_img_url,path_picture);
                                    dialog1.dismiss();
                                    if(result){
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(), "图片保存成功", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            }).start();

                        }
                    }
                });
                return false;
            }
        });
    }
}
