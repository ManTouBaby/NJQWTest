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
public class SPUtil {
    private static Manager mManager;

    private static SPUtil mSpUtil;

    private SPUtil(Context content, String name) {
        SharedPreferences mPreferences = content.getSharedPreferences(name, Context.MODE_PRIVATE);
        mManager = new Manager(mPreferences);
    }


    public static SPUtil INSTANCE(Context content, String name) {
        if (mSpUtil == null) {
            mSpUtil = new SPUtil(content, name);
        }
        return mSpUtil;
    }

    public static Manager MANAGER() {
        return mManager;
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
