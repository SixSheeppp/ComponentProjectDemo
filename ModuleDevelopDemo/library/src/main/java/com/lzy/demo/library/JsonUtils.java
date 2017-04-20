package com.lzy.demo.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json解析辅助工具类
 *
 * Created by zhuofeng on 2015/4/8.
 *
 */
public class JsonUtils {

    /**
     * 通过key获取value
     * @param json
     * @param key
     * @return
     */
    public static String getJSONObjectKeyVal(String json, String key){
        JSONObject object = null;
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(object == null){
            return "";
        }
        if(key == null){
            return "";
        }
        String result = null;
        Object obj;
        try {
            obj = object.get(key);
            if (obj == null || obj.equals(null)){
                result = "";
            }else{
                result = obj.toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将json转化为cls对象
     * @param jsonString json字符串
     * @param cls 对应的类
     * @return
     */
    public static <T> T json2Object(String jsonString, Class<T> cls) {
        T t = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            // 不转换没有 @Expose 注解的字段
            builder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将json转化为list对象
     * @param jsonString  json字符串
     * @param cls  对应的类
     * @return
     */
    public static <T> List<T> json2List(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(json2Object(jsonObject.toString(), cls));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json转化为List<Map<String, Object>>
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            GsonBuilder builder = new GsonBuilder();
            // 不转换没有 @Expose 注解的字段
            builder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            list = gson.fromJson(jsonString,
                    new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
