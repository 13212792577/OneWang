package com.wanglipeng.a32014.onewang.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wanglipeng on 2016/9/19.
 */
public class JsonToHomePage {

    public static String[] getHomePage(String data){
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.getJSONArray("data");
            int length = array.length();
            String[] arr = new String[length];
            for(int i=0;i<length;i++){
                arr[i] = array.getString(i);
            }

            return arr;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
