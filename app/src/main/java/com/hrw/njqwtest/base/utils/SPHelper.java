package com.hrw.njqwtest.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/19 11:10
 * @desc:
 */
public class SPHelper {
    public Manager manager;

    private static SPHelper mSpUtil;

    private SPHelper(Context content, String name) {
        SharedPreferences mPreferences = content.getSharedPreferences(name, Context.MODE_PRIVATE);
        manager = new Manager(mPreferences);
    }

    public static SPHelper getInstance(){
        if (mSpUtil==null){
            throw new NullPointerException("It must be instantiated before use the method");
        }
        return mSpUtil;
    }

    public static void instance(Context content, String name) {
        if (mSpUtil == null) {
            synchronized (SPHelper.class) {
                if (mSpUtil == null) {
                    mSpUtil = new SPHelper(content, name);
                }
            }
        }
    }


    public class Manager {
        private SharedPreferences mPreferences;

        Manager(SharedPreferences preferences) {
            this.mPreferences = preferences;
        }

        public void putObject(String objectKey, Object o) {
            String jsonString = JSON.toJSONString(o);
            System.out.println(jsonString);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(objectKey, jsonString);
            editor.apply();
        }

        public <T> T getObject(String objectKey, Class<T> tClass) {
            String objectJSON = mPreferences.getString(objectKey, "null");
            return JSONObject.parseObject(objectJSON, tClass);
        }
    }


}
