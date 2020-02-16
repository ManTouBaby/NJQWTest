package com.hrw.njqwtest.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author:MtBaby
 * @date:2020/02/15 14:21
 * @desc:
 */
public class ToastHelper {
    public static void showShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
