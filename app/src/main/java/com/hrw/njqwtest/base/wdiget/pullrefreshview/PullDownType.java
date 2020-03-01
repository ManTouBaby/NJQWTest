package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.content.Context;

import com.hrw.njqwtest.base.wdiget.pullrefreshview.downview.DefaultPullDownView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 14:58
 * @desc:
 */
public enum PullDownType {
    DEFAULT(DefaultPullDownView.class);

    Class<? extends BasePullDownView> mClass;

    PullDownType(Class<? extends BasePullDownView> aClass) {
        mClass = aClass;
    }

    public <T extends BasePullDownView> T instance(Context context) {
        Constructor<?>[] constructors = mClass.getConstructors();
        T view = null;
        try {
            view = (T) constructors[0].newInstance(context);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return view;
    }
}
