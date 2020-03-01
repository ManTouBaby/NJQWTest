package com.hrw.njqwtest.base.wdiget.pullrefreshview;

import android.content.Context;

import com.hrw.njqwtest.base.wdiget.pullrefreshview.upview.DefaultPullUpView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/02/19 14:58
 * @desc:
 */
public enum PullUpType {
    DEFAULT(DefaultPullUpView.class);

    Class<? extends BasePullUpView> mClass;

    PullUpType(Class<? extends BasePullUpView> aClass) {
        mClass = aClass;
    }

    public <T extends BasePullUpView> T instance(Context context) {
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
