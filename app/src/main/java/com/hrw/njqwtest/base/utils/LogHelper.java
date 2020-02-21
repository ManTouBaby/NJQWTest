package com.hrw.njqwtest.base.utils;

import android.util.Log;

/**
 * @author:MtBaby
 * @date:2020/02/15 14:22
 * @desc:
 */
public class LogHelper {
    private static boolean isShowLog = true;
    private static String TAG = "Mine";

    public static void d(String tag, String msg) {
        if (!isShowLog) return;
        if (tag == null) tag = TAG;

        Log.d(tag, msg);
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void e(String tag, String msg) {
        if (!isShowLog) return;
        if (tag == null) tag = TAG;

        Log.e(tag, msg);
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void i(String tag, String msg) {
        if (!isShowLog) return;
        if (tag == null) tag = TAG;

        Log.i(tag, msg);
    }

    public static void i(String msg) {
        i(null, msg);
    }
}
