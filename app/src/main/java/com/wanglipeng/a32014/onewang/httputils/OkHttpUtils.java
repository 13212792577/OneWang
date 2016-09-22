package com.wanglipeng.a32014.onewang.httputils;

import android.os.Handler;
import android.util.Log;

import com.wanglipeng.a32014.onewang.callback.CallBackData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanglipeng on 2016/9/20.
 */
public class OkHttpUtils {

    Handler handler = new Handler();
    CallBackData callBackData;

   static OkHttpUtils okHttpUtils = null;

    public static OkHttpUtils getOkHttpUtils(){
        if(okHttpUtils==null){
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    public void setCallBackData(CallBackData callBackData){
        this.callBackData = callBackData;
    }

    public void getDataByNetwork(final String path) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(path).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(callBackData!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String data = response.body().string();
                                if(data!=null){
                                    callBackData.getDataCallBack(data,path);
                                }else{
                                    Log.e("======","===OKhttp数据data为空====");
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
