package com.wanglipeng.a32014.onewang.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wanglipeng on 2016/9/20.
 */
public class JsonToReadPictureUrl {

    public static String[] getReadPictureUrl(String data){
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.getJSONArray("data");
            int length = array.length();
            String[] strings = new String[length];
            for(int i=0;i<length;i++){
                JSONObject object1 = array.getJSONObject(i);
                strings[i] = object1.getString("cover");
            }
            return strings;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static String[] getReadId(String data){
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.getJSONArray("data");
            int length = array.length();
            String[] strings = new String[length];
            for(int i=0;i<length;i++){
                JSONObject object1 = array.getJSONObject(i);
                strings[i] = object1.getString("id");
            }
            return strings;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
